package com.github.spike.timetracker.recentlycreatedsection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecentlyCreatedSection() {
    Column() {
        Row(
            modifier = Modifier.padding(
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
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .weight(1f)
            )
            Card(
                modifier = Modifier.size(30.dp),
                shape = CircleShape,
                elevation = 0.dp,
                onClick = {

                }
            ) {
                // Image(
                // painterResource(R.drawable.ic_right_arrow),
                // contentDescription = "",
                //  )
            }
        }
        LazyRow(
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            items(4) { currentCount ->
                RowItem(number = currentCount)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowItem(number: Int) {
    Row(
        modifier = Modifier.size(180.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.size(150.dp),
            shape = RectangleShape,
            elevation = 2.dp,
            backgroundColor = Color(0xFFE0F6FD),
            onClick = {

            }
            ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Mobile App",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(start=4.dp),
                    )
                Row() {
                    Image(
                        painterResource(R.drawable.ic_clock),
                        modifier = Modifier.size(20.dp),
                        contentDescription = "",
                    )
                    Text(
                        text = "2 Sep",
                        modifier = Modifier.padding(start=8.dp),
                    )
                }
                Text(
                    text = "High Priority",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start=4.dp),
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecentlyCreatedSectionPreview() {
    TimeTrackerTheme {
        RecentlyCreatedSection()
    }
}

