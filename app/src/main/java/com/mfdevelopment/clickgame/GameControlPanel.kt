package com.mfdevelopment.clickgame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Control Panel for starting the game
 */
@Composable
fun GameControlPanel(modifier: Modifier = Modifier, onControlClicked: (GameState) -> Unit) {
    val buttonMargins = 8.dp
    Column(
        modifier = modifier.padding(8.dp).padding(top = 8.dp)
    ) {
        Text(text = "Controls", modifier = Modifier.padding(start = 32.dp), fontSize = 16.sp)
        Row(modifier = Modifier.padding(8.dp)) {
            ButtonWithIcon(
                onClick = { onControlClicked(GameState.STARTED) },
                imageVector = Icons.Filled.PlayArrow,
                modifier = Modifier.padding(buttonMargins),
                text = "Start"
            )
            ButtonWithIcon(
                onClick = { onControlClicked(GameState.EXIT) },
                imageVector = Icons.Filled.ExitToApp,
                modifier = Modifier.padding(buttonMargins),
                text = "Exit"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameControlPanelPreview() {
    GameControlPanel(onControlClicked = {})
}