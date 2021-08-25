package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                BasicsOfModifiers()
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
private fun BasicsOfModifiers() {
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
private fun ColumnComposablePractice() {
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
private fun RowComposablePractice() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColumnComposablePractice()
}