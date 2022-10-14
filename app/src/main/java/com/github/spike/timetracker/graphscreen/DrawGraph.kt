package com.github.spike.timetracker.graphscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme

@Composable
fun DrawGraph(dailyData: Array<Int>, modifier: Modifier) {
    val roundedCornerShape = RoundedCornerShape(10.dp)
    Box(
        modifier = modifier
           // .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = modifier
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
                    val topLeftY = y1-70.dp.toPx()
                    val intervals = floatArrayOf(8.dp.toPx(), 10.dp.toPx())
                    drawLine(
                        color = Color.DarkGray,
                        pathEffect= PathEffect.dashPathEffect(intervals, 4.dp.toPx()),
                        start = Offset(x1, topLeftY),
                        end = Offset(x1, height),
                        strokeWidth= 1.dp.toPx()
                    )
                    drawRoundRect(
                        topLeft = Offset(x1-5.dp.toPx(), y1-5.dp.toPx()),
                        color = mainCurveColorDarker,
                        size = Size(width = 10.dp.toPx(), 10.dp.toPx())
//                    cornerRadius = CornerRadius(8f, 8f)
                    )

                    drawRoundRect(
                        topLeft = Offset(x1-30.dp.toPx(), topLeftY),
                        color = Color.Black,
                        size = Size(width = 80.dp.toPx(), 30.dp.toPx()),
                        cornerRadius = CornerRadius(4f, 4f)
                    )
                    this.drawContext.canvas.nativeCanvas.apply {
                        val textX = x1-12.dp.toPx()
                        val textY = y1-48.dp.toPx()
                        drawCircle(
                            radius = 7f,
                            center = Offset(textX-8.dp.toPx(), textY-5.dp.toPx()),
                            color = Color.Yellow
                        )
                        drawText(
                            "3 Tasks",
                            textX,
                            textY,
                            android.graphics.Paint()
                                .apply {
                                    this.color = android.graphics.Color.WHITE
                                    this.textSize = 16.dp.toPx()
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
    modifier: Modifier = Modifier
) {
    val spacerSize = 16.dp
    val buttonModifier = modifier.size(40.dp)
    val buttonBorder = BorderStroke(1.dp,Color.Gray)
    val spacerModifier = Modifier.size(spacerSize)
    val days = arrayOf("Mo", "Tu", "We", "Th", "Fr")
    val daySelected = 1

    Row() {
        Spacer(modifier = Modifier.size(74.dp))
        for (i in days.indices) {
            var textColor = Color.Black
            var backColor = Color.White
            if (i == daySelected) {
                textColor = backColor.also {
                    backColor = textColor
                }
            }
            Card(border= buttonBorder,
                modifier = buttonModifier,
                backgroundColor = backColor,
                contentColor = textColor
            ){
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = days[i],
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier=spacerModifier)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewIndicator() {
    val dailyData = arrayOf(2, 2, 3, 2, 2, 4, 5)
    TimeTrackerTheme {
        // DrawCubic()
        Column {
          //  DrawGraph(dailyData)
          //  DailyRow()
            CurvedChart()
            DailyRow()
        }
    }
}
// turn (2, 2, 3, 2, 2, 4, 5) into 200f, 200f, 300f, 200f, 200f, 400f, 500f
// yPoints
// dailyTasks


@Composable
fun CurvedChart(
    modifier: Modifier = Modifier,
    //(2, 2, 3, 2, 2, 4, 5)
    yPoints: List<Float> = listOf(
        300f, 300f, 200f, 300f, 300f, 100f, 100f
    ),
    yPointsMovingAverage: List<Float> = listOf(
        400f, 400f, 300f, 200f, 100f, 100f, 100f
    ),
) {
    var labelX = 0f
    var labelY = 0f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ){
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val spacing = this.size.width / yPoints.size
            val height = this.size.height
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
                    val selected = 2
                    if (i == selected) {
                        val previousX = spacing + (i - 1) * dayInterval
                        val x1 = (previousX + currentX) / 2f
                        val x2 = (previousX + currentX) / 2f
                        val y1 = yPoints[i - 1]
                        val y2 = yPoints[i]
                        normX.add(currentX)
                        normY.add(yPoints[i])
                        val topLeftY = y2-70.dp.toPx()
                        val intervals = floatArrayOf(20f, 30f)
                        drawLine(
                            color = Color.DarkGray,
                            pathEffect= PathEffect.dashPathEffect(intervals, 4.dp.toPx()),
                            start = Offset(currentX, topLeftY),
                            end = Offset(currentX, height),
                            strokeWidth= 1.dp.toPx()
                        )
                        val mainCurveColor = Color(0xFFFDC698)
                        val mainCurveColorDarker = Color(0xFFEEBD94)

                      //  val intervals = floatArrayOf(8.dp.toPx(), 10.dp.toPx())
                        drawRoundRect(
                            topLeft = Offset(currentX-5.dp.toPx(), y2-5.dp.toPx()),
                            color = mainCurveColorDarker,
                            size = Size(width = 10.dp.toPx(), 10.dp.toPx()),
//                    cornerRadius = CornerRadius(8f, 8f)
                        )
                        drawRoundRect(
                            topLeft = Offset(currentX-30.dp.toPx(), topLeftY),
                            color = Color.Black,
                            size = Size(width = 80.dp.toPx(), 30.dp.toPx()),
                            cornerRadius = CornerRadius(4f, 4f)
                        )
                        labelX = currentX-30.dp.toPx()
                        labelY = topLeftY

                    }

                }

            }
            this.drawContext.canvas.nativeCanvas.apply {
                val textX = labelX-12.dp.toPx()
                val textY = labelY-48.dp.toPx()
                drawCircle(
                    radius = 7f,
                    center = Offset(textX-8.dp.toPx(), textY-5.dp.toPx()),
                    color = Color.Yellow
                )
                drawText(
                    "3 Tasks",
                    textX,
                    textY,
                    android.graphics.Paint()
                        .apply {
                            this.color = android.graphics.Color.RED
                            this.textSize = 16.dp.toPx()
                        }
                )
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
                color = Color(0xB3FDC698),
               // color = Color(0xFFFDC698),
                style = Stroke(
                    width = 4.dp.toPx(),
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
