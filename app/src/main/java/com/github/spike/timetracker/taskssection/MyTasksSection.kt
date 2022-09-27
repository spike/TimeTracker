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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.R


@Composable
fun MyTasksSection() {
    LazyColumn (modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth(),
        contentPadding= PaddingValues(bottom = 40.dp)
    ) {
        item {
            Text(
                text = "My Tasks",
                style = MaterialTheme.typography.h4,
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
                        .size(80.dp)
                        .padding(start = 8.dp),
                    shape = RectangleShape,
                    elevation = 2.dp,
                    backgroundColor = Color.Gray
                ) {
               Image(
                   painterResource(id = R.drawable.ic_clipboard),
                   contentDescription = ""
               )
                }

                Text(text = "To Do",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start=16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .weight(.1f)
                )
                Text(text = "5 tasks",
                    fontFamily = FontFamily.Default,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.size(100.dp)
                )


            }
            Divider(
                modifier = Modifier.padding(
                    start = 28.dp,
                    end = 32.dp,
                    top = 16.dp,
                    bottom = 16.dp
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