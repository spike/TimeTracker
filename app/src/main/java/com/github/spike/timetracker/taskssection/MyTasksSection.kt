package com.github.spike.timetracker.taskssection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.R
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme


@Composable
fun MyTasksSection() {
    val tasks = arrayOf(
        arrayOf("To Do", "5 tasks"),
        arrayOf("In Progress",  "3 tasks"),
        arrayOf("Done",  "25 tasks"),
    )
    val color_chips = arrayOf(
        0xFFFDC698,
        0xFFE0F6FD,
        0xFFF3FD7F
    )
    val chip_icons = arrayOf(
        R.drawable.ic_clipboard,
        R.drawable.ic_pending_clipboard,
        R.drawable.ic_check_mark_within_circle
    )
    LazyColumn (modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth(),
        contentPadding= PaddingValues(bottom = 40.dp)
    ) {
        item {
            Text(
                text = "My Tasks",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(
                    start = 30.dp,
                    top = 8.dp,
                    bottom = 20.dp),
            )
        }
        items(3) { index ->
            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 8.dp),
                    shape = RectangleShape,
                    elevation = 2.dp,
                    backgroundColor = Color(color_chips[index])
                ) {
               Image(
                   painterResource(id = chip_icons[index]),
                   contentDescription = ""
               )
                }

                Text(text = tasks[index][0],
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start=16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .weight(.1f)
                )
                Text(text = tasks[index][1],
                    fontFamily = FontFamily.Default,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.width(100.dp)
                )


            }
            Divider(
                modifier = Modifier.padding(
                    start = 28.dp,
                    end = 32.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewTasks() {
    MyTasksSection()
}