package com.github.spike.timetracker.recentlycreatedsection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme
import com.github.spike.timetracker.R


val tasks = arrayOf("Mobile App", "Web", "Email")
val dates = arrayOf("2 Sep", "8 Oct", "10 Dec")
val priorities= arrayOf("High Priority", "Low Priority", "Low Priority")
val colors=arrayOf(0xFFFEEDDE, 0xFFE0F6FD, 0xFFF3FD7F)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecentlyCreatedSection(modifier: Modifier) {
    Column() {
        Row(
            modifier = modifier.padding(
                start = 24.dp,
                end = 32.dp
            )
        ) {
            Text(
                text = "Recently Created",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = modifier
                    .width(IntrinsicSize.Max)
                    .weight(1f)
            )
            Card(
                modifier = modifier.size(30.dp),
                shape = CircleShape,
                elevation = 0.dp,
                onClick = {

                }
            ) {
                Image (
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = ""
                        )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            items(4) { currentCount ->
                RowItem(index = currentCount, modifier)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowItem(index: Int, modifier: Modifier) {
    Row(
        modifier = modifier.size(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier.size(180.dp),
            shape = RoundedCornerShape(8),
            elevation = 2.dp,
            backgroundColor = Color(colors[index]),
            onClick = {

            }
            ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier=modifier.padding(start=20.dp, end=30.dp)
            ) {
                    Text(text = tasks[index],
                        style = MaterialTheme.typography.h6,
                        modifier = modifier.padding(),
                    )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(R.drawable.ic_clock),
                        modifier = modifier.size(20.dp),
                        contentDescription = "",
                    )
                    Text(
                        text = dates[index],
                        modifier = modifier.padding(start=8.dp),
                    )
                }
                Text(
                    text = priorities[index],
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier.padding(),
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecentlyCreatedSectionPreview() {
    TimeTrackerTheme {
        RecentlyCreatedSection(Modifier)
    }
}

