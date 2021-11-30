package com.mfdevelopment.clickgame

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfdevelopment.clickgame.ui.theme.ClickGameTheme

class MainActivity : ComponentActivity() {
    private val paddingStart = 8.dp
    private val paddingEnd = paddingStart
    private val paddingTop = paddingStart
    private val paddingBottom = paddingStart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(
                        start = paddingStart,
                        end = paddingEnd,
                        top = paddingTop,
                        bottom = paddingBottom
                    )
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content(initialGameState: GameState = GameState.INIT) {
    var gameState: GameState by remember { mutableStateOf(initialGameState) }
    var counter: Int by remember { mutableStateOf(0) }

    when (gameState) {
        GameState.INIT -> {
            // Show a control panel in the center of the screen
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                GameControlPanel {
                    gameState = it
                }
            }
        }
        GameState.STARTED -> {
            Column(Modifier.fillMaxSize()) {
                Row(modifier = Modifier.padding(8.dp)) {
                    CounterText(counter, modifier = Modifier.weight(1f))
                    Timer(timeoutSeconds = 10, onTimeout = {
                        Log.d("test", "timeout")
                        gameState = GameState.STOPPED
                    })
                }
                Spacer(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                GameArea(modifier = Modifier
                    .fillMaxSize(),
                    onItemClicked = {
                        Log.d("test", "item clicked")
                        counter += 1
                    })
            }
        }
        GameState.STOPPED -> {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameResultPanel(score = counter, onControlClicked = {
                    gameState = it
                })
            }
        }
        GameState.EXIT -> {
            val activity = (LocalContext.current as? Activity)
            activity?.finish()
        }
        else -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                GameControlPanel {
                    gameState = it
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitPreview() {
    ClickGameTheme {
        Content(GameState.INIT)
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    ClickGameTheme {
        Content(GameState.STARTED)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    ClickGameTheme {
        Content(GameState.STOPPED)
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun CounterText(count: Int = 0, modifier: Modifier = Modifier) {
    Text(
        text = "$count Punkte",
        modifier = modifier
    )
}

@Composable
fun Timer(timeoutSeconds: Long, onTimeout: () -> Unit) {
    var remainingTime by remember { mutableStateOf(timeoutSeconds) }
    val countdown by remember {
        mutableStateOf(
            CountDown.createCountDownTimer(
                timeoutSeconds,
                onTick = { remainingTime = it },
                onFinish = {
                    remainingTime = 0
                    onTimeout()
                }).start()
        )
    }

    Text(text = "${String.format("%.2f", remainingTime.toFloat() / 1000)} seconds")
}

