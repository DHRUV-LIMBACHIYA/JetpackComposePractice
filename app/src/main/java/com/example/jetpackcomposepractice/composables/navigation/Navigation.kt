package com.example.jetpackcomposepractice.composables.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

@Composable
fun Navigation() {
    // Get the nav controller.
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
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