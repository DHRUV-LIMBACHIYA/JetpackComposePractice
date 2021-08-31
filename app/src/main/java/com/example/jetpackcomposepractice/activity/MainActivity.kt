package com.example.jetpackcomposepractice.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.composables.BoxAnimation
import com.example.jetpackcomposepractice.composables.CircularProgressBar
import com.example.jetpackcomposepractice.composables.MusicKnob
import com.example.jetpackcomposepractice.composables.VolumeBar
import com.example.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme(darkTheme = false) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF101010)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                            .padding(30.dp)
                    ) {
                        var volume by remember {
                            mutableStateOf(0f)
                        }

                        var barCount by remember {
                            mutableStateOf(20)
                        }

                        MusicKnob(
                            modifier = Modifier.size(100.dp)
                        ) {
                            volume = it
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        VolumeBar(
                            modifier = Modifier.fillMaxWidth()
                                .height(100.dp),
                            activeCount = (barCount * volume).toInt(),
                            barCount = barCount
                        )
                    }
                }
            }
        }
    }
}





