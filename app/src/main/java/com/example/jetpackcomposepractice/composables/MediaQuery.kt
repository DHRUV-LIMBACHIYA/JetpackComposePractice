package com.example.jetpackcomposepractice.composables

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Dhruv Limbachiya on 06-09-2021.
 */

sealed class Dimensions {
    object Width : Dimensions()
    object Height : Dimensions()

    sealed class DimensionOperator {
        object LessThan : DimensionOperator()
        object GreaterThan : DimensionOperator()
    }


    class DimensionComparator(
        val operator: DimensionOperator,
        private val dimensions: Dimensions,
        val value: Dp
    ) {
        fun compare(screenWidth: Dp, screenHeight: Dp): Boolean {
            return if (dimensions is Width) {
                when (operator) {
                    is DimensionOperator.LessThan -> screenWidth < value
                    is DimensionOperator.GreaterThan -> screenWidth > value
                }
            } else {
                when (operator) {
                    is DimensionOperator.LessThan -> screenHeight < value
                    is DimensionOperator.GreaterThan -> screenHeight > value
                }
            }
        }
    }
}

//@Composable
//fun MediaQuery(
//    comparator: Dimensions.DimensionComparator,
//    content: @Composable () -> Unit
//) {
//    // Get the device screen size in dp.
//    val screenWidth =
//        LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density
//    val screenHeight =
//        LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
//
//    // Display only if the comparison return true.
//    if (comparator.compare(screenWidth, screenHeight)) {
//        content()
//    }
//}

/**
 * Infix functions
 */
infix fun Dimensions.lessThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        value = value,
        dimensions = this
    )
}

infix fun Dimensions.greaterThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        value = value,
        dimensions = this
    )
}

@Composable
fun Modifier.mediaQuery(
    comparator: Dimensions.DimensionComparator,
    modifier: Modifier
): Modifier = composed {
    // Get the device screen size in dp.
    val screenWidth =
        LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density
    val screenHeight =
        LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density

    if (comparator.compare(screenWidth, screenHeight)) {
        this.then(modifier)
    } else {
        this
    }
}