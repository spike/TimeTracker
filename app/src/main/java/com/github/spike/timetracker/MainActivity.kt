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
                    ScaffoldLayout(Modifier)
                }
            }
        }
    }
}
@Composable
fun ScaffoldLayout(modifier: Modifier) {
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
                MyTasksSection(modifier)
                RecentlyCreatedSection(modifier)
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
        ScaffoldLayout(Modifier)
    }
}