package com.github.spike.timetracker.graphscreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.spike.timetracker.ui.theme.TimeTrackerTheme

class GraphSection : ComponentActivity() {
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
                    "Daily Tasks Done",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black,
                )
                    Spacer(modifier = modifier.size(60.dp))
                    Text(
                        "5 OCT, 2022",
                        style = MaterialTheme.typography.h6,
                    )
                        },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = {
            Column {
                CurvedChart()
                DailyRow()
                DoneTasks(modifier)
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