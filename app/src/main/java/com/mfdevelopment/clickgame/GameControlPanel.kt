package com.mfdevelopment.clickgame

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Control Panel for starting the game
 */
@Composable
fun GameControlPanel(modifier: Modifier = Modifier, onControlClicked: (GameState) -> Unit) {
    val buttonMargins = 8.dp
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(text = "Controls", modifier = Modifier.padding(start = 24.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = { onControlClicked(GameState.STARTED) },
                modifier = Modifier.padding(buttonMargins)
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Start",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Start")
            }
            Button(
                onClick = { onControlClicked(GameState.STOPPED) },
                modifier = Modifier.padding(buttonMargins)
            ) {
                Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = "Exit",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Exit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameControlPanelPreview() {
    GameControlPanel(onControlClicked = {})
}