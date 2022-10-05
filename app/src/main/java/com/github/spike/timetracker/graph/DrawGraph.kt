package com.github.spike.timetracker.graph

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme

@Composable
fun DrawGraph(dailyData: Array<Int>) {
    val roundedCornerShape = RoundedCornerShape(10.dp)
    Box(
        modifier = Modifier
           // .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .height(280.dp)
                .fillMaxWidth(1f)
                .graphicsLayer { // call this function to apply custom shadow elevation, otherwise use `clip()`
                    //  shadowElevation = 45f
                    clip = true
                    shape = roundedCornerShape
                },
        ) {
            val mainCurveColor = Color(0xFFFDC698)
            val mainCurveColorDarker = Color(0xFFEEBD94)
            val movingAverageCurveColor = Color.Black

            var max = 0
            for (i in dailyData.indices) {
                max = Math.max(dailyData[i], max)
            }
            max += 1
            var selected = 3
           // val width = 300.dp.toPx()
            val width = this.size.width
            val height = this.size.height
            val intervalWidth = width / 5f
            val intervalHeight = height / max
            for (i in 1..dailyData.lastIndex) {
                val x1 = (i-1f) * intervalWidth
                val y1 = (max-dailyData[i-1].toFloat()) * intervalHeight
                val x2 = i * intervalWidth
                val y2 = (max-dailyData[i].toFloat()) * intervalHeight

                drawLine(
                    color = mainCurveColor,
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    strokeWidth = 4.dp.toPx()
                )
                if (selected == i) {

                    val intervals = floatArrayOf(8.dp.toPx(), 10.dp.toPx())
                    drawLine(
                        color = Color.DarkGray,
                        pathEffect= PathEffect.dashPathEffect(intervals, 4.dp.toPx()),
                        start = Offset(x1, 0f),
                        end = Offset(x1, height),
                        strokeWidth= 1.dp.toPx()
                    )
//                    drawCircle(
//                        color = mainCurveColorDarker,
//                        center = Offset(x1, y1),
//                        radius = 5.dp.toPx(),
//                    )
                    drawRoundRect(
                        topLeft = Offset(x1-5.dp.toPx(), y1-5.dp.toPx()),
                        color = mainCurveColorDarker,
                        size = Size(width = 10.dp.toPx(), 10.dp.toPx())
//                    cornerRadius = CornerRadius(8f, 8f)
                    )
                    drawRect(
                        topLeft = Offset(x1-30.dp.toPx(), y1-70.dp.toPx()),
                        color = Color.Black,
                        size = Size(width = 80.dp.toPx(), 30.dp.toPx())
//                    cornerRadius = CornerRadius(8f, 8f)
                    )
                    this.drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "3 Tasks",
                            x1-18.dp.toPx(),
                            y1-48.dp.toPx(),
                            android.graphics.Paint()
                                .apply {
                                    this.color = android.graphics.Color.WHITE
                                    this.textSize = 18.dp.toPx()
                                }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun DailyRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    horizontalArrangement: Arrangement.HorizontalOrVertical = Arrangement.SpaceEvenly
) {
    val spacerSize = 10.dp
    val buttonSize = 40.dp
    Row() {
        Spacer(modifier = Modifier.size(80.dp))
        Card(border= BorderStroke(2.dp,Color.DarkGray),
            modifier = Modifier.size(40.dp).align(
                Alignment.CenterVertically
            ),

            ) {
            Text("  Mo")
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Card(border= BorderStroke(2.dp,Color.DarkGray),
            modifier = Modifier.size(40.dp).align(
                Alignment.CenterVertically
            ),

            ) {
            Text("  Tu")
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Card(border= BorderStroke(2.dp,Color.DarkGray),
            modifier = Modifier.size(40.dp).align(
                Alignment.CenterVertically
            ),

        ) {
            Text("  We")
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Card(border= BorderStroke(2.dp,Color.DarkGray),
            modifier = Modifier.size(40.dp).align(
                Alignment.CenterVertically
            ),

            ) {
            Text("  Th")
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Card(border= BorderStroke(2.dp,Color.DarkGray),
            modifier = Modifier.size(40.dp).align(
                Alignment.CenterVertically
            ),

            ) {
            Text("  Fr")
        }
        Spacer(modifier = Modifier.size(spacerSize))
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewIndicator() {
    val dailyData = arrayOf(2, 2, 3, 2, 2, 4, 5)
    TimeTrackerTheme {
        // DrawCubic()
        Column {
            DrawGraph(dailyData)
            DailyRow()
            CurvedChart()
        }
    }
}

// code from https://stackoverflow.com/a/73107907/320111
@Composable
fun CurvedChart(
    modifier: Modifier = Modifier,
    yPoints: List<Float> = listOf(
        410f, 300f, 375f, 380f, 180f
    ),
    yPointsMovingAverage: List<Float> = listOf(
        500f, 390f, 380f, 320f, 100f
    ),
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ){

        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val spacing = this.size.width / yPoints.size
            val dayInterval = (size.width - spacing) / yPoints.size

            val normX = mutableListOf<Float>()
            val normY = mutableListOf<Float>()

            val strokePath = Path().apply {

                for (i in yPoints.indices) {

                    val currentX = spacing + i * dayInterval

                    if (i == 0) {

                        moveTo(currentX, yPoints[i])
                    } else {

                        val previousX = spacing + (i - 1) * dayInterval
                        val conX1 = (previousX + currentX) / 2f
                        val conX2 = (previousX + currentX) / 2f
                        val conY1 = yPoints[i - 1]
                        val conY2 = yPoints[i]

                        cubicTo(
                            x1 = conX1,
                            y1 = conY1,
                            x2 = conX2,
                            y2 = conY2,
                            x3 = currentX,
                            y3 = yPoints[i]
                        )
                    }

                    // Circle dot points
                    normX.add(currentX)
                    normY.add(yPoints[i])

                }
            }

            val strokePathDottedLine = Path().apply {

                for (i in yPoints.indices) {

                    val currentX = spacing + i * dayInterval

                    if (i == 0) {

                        moveTo(currentX, yPointsMovingAverage[i])
                    } else {
                        val previousX = spacing + (i - 1) * dayInterval
                        val conX1 = (previousX + currentX) / 2f
                        val conX2 = (previousX + currentX) / 2f
                        val conY1 = yPointsMovingAverage[i - 1]
                        val conY2 = yPointsMovingAverage[i]

                        cubicTo(
                            x1 = conX1,
                            y1 = conY1,
                            x2 = conX2,
                            y2 = conY2,
                            x3 = currentX,
                            y3 = yPointsMovingAverage[i]
                        )
                    }
                }
            }

            val intervals = floatArrayOf(20f, 30f)
            drawPath(
                path = strokePathDottedLine,
                color = Color.Gray,
                style = Stroke(
                    width = 2.dp.toPx(),
                    cap = StrokeCap.Round,
                    pathEffect= PathEffect.dashPathEffect(intervals, 8f),

                    )
            )

            drawPath(
                path = strokePath,
                color = Color(0xFFFDC698),
                style = Stroke(
                    width = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )

            (normX.indices).forEach {
                drawCircle(
                    Color(0xFFFDC698),
                    radius = 3.dp.toPx(),
                    center = Offset(normX[it], normY[it])
                )
            }
        }
    }
}