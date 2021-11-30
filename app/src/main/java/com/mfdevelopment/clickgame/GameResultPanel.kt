package com.mfdevelopment.clickgame

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameResultPanel(
    modifier: Modifier = Modifier,
    score: Int,
    onControlClicked: (GameState) -> Unit
) {
    val minButtonWidth = 20.dp
    Column(
        modifier = modifier.padding(8.dp).padding(top = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Score: $score", fontSize = 16.sp, modifier = Modifier.padding(start = 32.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            ButtonWithIcon(
                onClick = { onControlClicked(GameState.STARTED) },
                imageVector = Icons.Filled.Refresh,
                text = "Play again",
                modifier = Modifier.defaultMinSize(minWidth = minButtonWidth)
            )
            Spacer(modifier = Modifier.size(8.dp))
            ButtonWithIcon(
                onClick = { onControlClicked(GameState.EXIT) },
                imageVector = Icons.Filled.ExitToApp,
                text = "Exit",
                modifier = Modifier.defaultMinSize(minWidth = minButtonWidth)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameResultPanelPreview() {
    GameResultPanel(score = 12, onControlClicked = {})
}