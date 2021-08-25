package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.kermit)
            val title = "This is Kermit in the snow"
            val contentDescription = "This is Kermit in the snow"

            ImageCard(painter = painter, title = title, contentDescription = contentDescription)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsOfModifiers()
}