package com.example.jetpackcomposepractice.composables.navigation

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

sealed class Screen(val route: String){
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object Notification : Screen("notification_screen")
    object Settings : Screen("settings_screen")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route) // ex: DetailScreen.route(detail_screen)
            args.forEach { arg ->
                append("/$arg")  // assume arg1 = "Dhruv" then route will be : detail_screen/Dhruv
            }
        }
    }
}
