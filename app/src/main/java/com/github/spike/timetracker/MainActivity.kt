package com.github.spike.timetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme
import com.github.spike.timetracker.R
import com.github.spike.timetracker.graph.CurvedChart
import com.github.spike.timetracker.graph.DrawGraph
import com.github.spike.timetracker.recentlycreatedsection.RecentlyCreatedSection
import com.github.spike.timetracker.taskssection.MyTasksSection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldLayout()
                }
            }
        }
    }
}
@Composable
fun ScaffoldLayout() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text(
                    "Task Manager",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black,
                )},
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = {
            Column {
                MyTasksSection()
                RecentlyCreatedSection()
              //  DrawCubic()
//                val dailyData = arrayOf(2, 2, 3, 2, 2, 5, 5)
//                Column() {
//                    DrawGraph(dailyData)
//                    CurvedChart()
//                }
            }
        },
        bottomBar = {
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimeTrackerTheme {
        ScaffoldLayout()
    }
}