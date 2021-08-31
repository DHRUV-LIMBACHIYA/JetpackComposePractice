package com.example.jetpackcomposepractice.composables

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import com.example.jetpackcomposepractice.R
import kotlin.math.PI
import kotlin.math.atan2

/**
 * Created by Dhruv Limbachiya on 31-08-2021.
 */

@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitedAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {

    // State for holding rotation value of MusicKnob.
    var rotation by remember {
        mutableStateOf(limitedAngle)
    }

    // States for holding center position of [music_knob]
    var centerX by remember {
        mutableStateOf(0f)
    }

    var centerY by remember {
        mutableStateOf(0f)
    }

    // States of holding touch position.
    var touchX by remember {
        mutableStateOf(0f)
    }

    var touchY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            // Invoke this lambda when Image knob render during composition or recomposition.
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y

                val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitedAngle..limitedAngle) {
                            val fixedAngle = if (angle in -180f..-limitedAngle) {
                                360f + angle
                            } else {
                                angle
                            }

                            rotation = fixedAngle
                            val percent =
                                (fixedAngle - limitedAngle) / (360f - 2 * limitedAngle) // Give a percent between 0 to 1
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )

}


@Composable
fun VolumeBar(
    modifier: Modifier,
    activeCount: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount) //  2 * barCount = 1 for bar & another 1 for space between two bars.
        }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount){
                drawRoundRect(
                    color = if(i in 0..activeCount) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f,0f),
                    size = Size(barWidth,constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}