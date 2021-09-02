package com.example.jetpackcomposepractice.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Dhruv Limbachiya on 30-08-2021.
 */

@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    title: String,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
) {

    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }

    // State for holding alpha value of Box.
    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1f else 0f, // Animate from 0f to 1f when isOpen = true and 1f to 0f when isOpen = false.
        animationSpec = tween(
            300
        )
    )

    // State for rotating X axis of Box
    val rotateXAxis = animateFloatAsState(
        targetValue = if (isOpen) 0f else -90f,
        animationSpec = tween(
            300
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        // Row for displaying Title text and Drop Down button.
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title Text
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )

            // Drop Down Icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop Down Icon",
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .size(24.dp)
                    .scale(1f, if (isOpen) -1f else 1f),
                tint = Color.White,
                )
        }

        // Box containing content
        Box(
            modifier = modifier
                .fillMaxWidth()
                .graphicsLayer {
                    // Offset percentage along the x and y axis for which contents are rotated and scaled.Default x = 0.5f & y = 0.5f (Center)
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    rotationX = rotateXAxis.value // Rotate X axis
                }
                .alpha(alpha.value)
                .height(100.dp)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun CircularProgressBar(
    maxValue: Int,
    percentage: Float,
    color: Color = Color.Green,
    fontSize: TextUnit = 20.sp,
    radius: Dp = 50.dp,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = color,
                -90f,
                360 * currentPercentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (currentPercentage * maxValue).toInt().toString(),
            fontSize = fontSize,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BoxAnimation() {
    var size by remember {
        mutableStateOf(200.dp)
    }

    val sizeState by animateDpAsState(
        targetValue = size,
        tween(
            durationMillis = 1000
        )
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .size(sizeState)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { size += 50.dp }) {
            Text(text = "Increase size")
        }
    }
}
