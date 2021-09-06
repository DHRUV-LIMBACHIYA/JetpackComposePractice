package com.example.jetpackcomposepractice.composables.navigation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.jetpackcomposepractice.R
import kotlinx.coroutines.delay

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

@Composable
fun Navigation() {
    // Get the nav controller.
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Dhruv"
                    nullable = true
                }
            )
        ) {
            DetailScreen(name = it.arguments?.getString("name"))
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        val scale = remember {
           Animatable(0f)
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.5f,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )

            delay(3000L)

            navController.navigate(Screen.MainScreen.route)
        }

        Image(
            painter = painterResource(id = R.drawable.netflix_splash),
            contentDescription = "netflix",
            Modifier.scale(scale.value)
        )
    }
}

/**
 * Main Screen : Starting destination for NavHost.
 */
@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var nameText by remember {
            mutableStateOf("")
        }

        TextField(
            value = nameText,
            onValueChange = {
                nameText = it
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(nameText))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To Detail Screen")
        }
    }
}

/**
 * Detail Screen : Containing text which is passed from MainScreen.
 */
@Composable
fun DetailScreen(name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello, $name")
    }
}