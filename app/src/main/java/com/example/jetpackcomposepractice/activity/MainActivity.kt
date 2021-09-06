package com.example.jetpackcomposepractice.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.composables.Dimensions
import com.example.jetpackcomposepractice.composables.lessThan
import com.example.jetpackcomposepractice.composables.mediaQuery
import com.example.jetpackcomposepractice.composables.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }

}


@Composable
fun MediaQueryComposable() {
    Text(
        text = "I will only be visible in width of 500dp", modifier = Modifier
            .background(Color.Green)
            .mediaQuery(
                Dimensions.Width lessThan 500.dp,
                Modifier.size(450.dp)
            )
    )
}