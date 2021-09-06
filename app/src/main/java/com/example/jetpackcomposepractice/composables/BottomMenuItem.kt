package com.example.jetpackcomposepractice.composables

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

data class BottomMenuItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
