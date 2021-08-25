package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RowComposable()
        }
    }

}

@Composable
private fun RowComposable() {
    Row(
        modifier = Modifier
            .fillMaxSize(0.7f)
            .background(Color.Green),
        horizontalArrangement = Arrangement.End,  // Main Axis
        verticalAlignment = Alignment.CenterVertically // Cross Axis
    ) {
        Text("Hello", Modifier.background(Color.Red))
        Text("World", Modifier.background(Color.Blue))
        Text("Compose", Modifier.background(Color.Magenta))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RowComposable()
}