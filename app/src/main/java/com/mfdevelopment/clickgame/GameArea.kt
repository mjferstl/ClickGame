package com.mfdevelopment.clickgame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.mfdevelopment.clickgame.ui.theme.ClickGameTheme
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun GameArea(modifier: Modifier = Modifier, onItemClicked: () -> Unit) {

    // constants
    val minRadius = 25f
    val maxRadius = 100f
    val tolerance = 20f

    // variables
    var redrawCount: Int by remember {
        mutableStateOf(0)
    }
    var xPos = 0f
    var yPos = 0f
    var radius = 0f

    Canvas(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures { offset: Offset ->
                run {
                    if (isItemClicked(
                            itemPosX = xPos,
                            itemPosY = yPos,
                            itemRadius = radius,
                            tapPosX = offset.x,
                            tapPosY = offset.y,
                            tolerance = tolerance
                        )
                    ) {
                        onItemClicked()
                        redrawCount++
                    }
                }
            }
        }) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val random = Random()
        // radius
        radius = random.nextFloat() * (maxRadius - minRadius) + minRadius

        // Center of the item
        xPos = min(max(random.nextFloat() * canvasWidth + redrawCount * 0, radius),canvasWidth-radius)
        yPos = min(max(random.nextFloat() * canvasHeight, radius), canvasHeight-radius)

        drawCircle(
            color = Color.Red,
            radius = radius,
            center = Offset(xPos, yPos)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameAreaPreview() {
    GameArea(onItemClicked = {

    })
}

/**
 * Check if the item has been clicked
 */
fun isItemClicked(
    itemPosX: Float,
    itemPosY: Float,
    itemRadius: Float,
    tapPosX: Float,
    tapPosY: Float,
    tolerance: Float = 20f
): Boolean {
    return sqrt(
        (itemPosX - tapPosX).toDouble().pow(2.0) + (itemPosY - tapPosY).toDouble().pow(2.0)
    ) <= (itemRadius + tolerance)
}
