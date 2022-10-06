package com.github.spike.timetracker.graphscreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme


@Composable
fun DoneTasks() {
    val tasks = arrayOf(
        arrayOf("Leetcode", "5 tasks"),
        arrayOf("Laundry",  "3 tasks"),
        arrayOf("Groceries",  "25 tasks"),
    )
    val color_chips = arrayOf(
        0xFFFDC698,
        0xFFE0F6FD,
        0xFFF3FD7F
    )
    val compose_icons = arrayOf(
        Icons.Rounded.Menu,
        Icons.Rounded.Email,
        Icons.Rounded.AccountBox,
        Icons.Rounded.Info
    )
    LazyColumn (modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth(),
        contentPadding= PaddingValues(bottom = 40.dp)
    ) {
        item {
            Text(
                text = "Done Tasks",
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
                    Icon(compose_icons[index],
                    contentDescription = "")
//               Image(
//                   painterResource(id = chip_icons[index]),
//                  // Icons.Default.Done,
//                   contentDescription = ""
//               )
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
//                Text(text = tasks[index][1],
//                    fontFamily = FontFamily.Default,
//                    style = MaterialTheme.typography.h6,
//                    modifier = Modifier.width(100.dp)
//                )


            }
            Spacer(
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
    TimeTrackerTheme {
        DoneTasks()
    }
}