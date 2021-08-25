package com.example.jetpackcomposepractice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Dhruv Limbachiya on 25-08-2021.
 */


@Composable
fun ImageCard(
    painter: Painter,
    title: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
            ){}

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}


/**
 * Composable demonstrate basic of modifiers.
 * - chaining of modifiers method.
 * - offset vs margin.
 * - padding between views.
 * - Spacer for adding space.
 * - interaction(clickable).
 */
@Composable
fun BasicsOfModifiers() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .border(10.dp, Color.Magenta)
            .padding(10.dp)
            .border(10.dp, Color.Blue)
            .padding(10.dp)
            .border(10.dp, Color.Cyan)
            .padding(10.dp)
    ) {
        Text(text = "hello", modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .clickable { /* NO-OP */ })
        Spacer(modifier = Modifier.height(20.dp))
        Text("world", modifier = Modifier.offset(50.dp, 20.dp))
    }
}

@Composable
fun ColumnComposablePractice() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.Magenta),
        verticalArrangement = Arrangement.SpaceEvenly, // Main Axis
        horizontalAlignment = Alignment.CenterHorizontally // Cross Axis
    ) {
        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 1")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 2")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 3")
        }
    }
}

@Composable
fun RowComposablePractice() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.Green)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,  // Main Axis
        verticalAlignment = Alignment.CenterVertically // Cross Axis
    ) {
        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 4")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 5")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 6")
        }
    }
}