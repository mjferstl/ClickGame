package com.mfdevelopment.clickgame

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithIcon(modifier: Modifier = Modifier, onClick: () -> Unit, imageVector: ImageVector, text: String = "", buttonMargin: Dp = 8.dp) {
    Button(
        onClick = { onClick() },
        modifier = modifier.padding(buttonMargin)
    ) {
        Icon(
            imageVector,
            contentDescription = text,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        if (text.isNotEmpty()) {
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text)
        }
    }
}