package com.example.jetpackcomposepractice.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposepractice.composables.BottomMenuItem
import com.example.jetpackcomposepractice.composables.Dimensions
import com.example.jetpackcomposepractice.composables.lessThan
import com.example.jetpackcomposepractice.composables.mediaQuery
import com.example.jetpackcomposepractice.composables.navigation.BottomNavigationBar
import com.example.jetpackcomposepractice.composables.navigation.Navigation
import com.example.jetpackcomposepractice.composables.navigation.Screen

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigationBar(
                        list = listOf(
                            BottomMenuItem("Home",Screen.MainScreen.route, Icons.Default.Home),
                            BottomMenuItem("Notifications",Screen.Notification.route, Icons.Default.Notifications, badgeCount = 432),
                            BottomMenuItem("Settings",Screen.Settings.route, Icons.Default.Settings),
                        ),
                        navController = navController,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        navController.navigate(it.route)
                    }
                }
            ) {
                Navigation(navController)
            }
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