package com.example.jetpackcomposepractice.composables.navigation

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String) : String {
        return buildString {
            append(route) // ex: DetailScreen.route(detail_screen)
            args.forEach { arg ->
                append("/$arg")  // assume arg1 = "Dhruv" then route will be : detail_screen/Dhruv
            }
        }
    }
}
