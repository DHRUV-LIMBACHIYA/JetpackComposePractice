package com.example.jetpackcomposepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

/**
 * Created by Dhruv Limbachiya on 26-08-2021.
 */

@Composable
fun DemonstrateConstraintLayout() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox") // Equivalent to creating id of view in constrainLayout in XML.
        val redBox = createRefFor("redBox")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            start.linkTo(parent.start)
            top.linkTo(guideline)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            start.linkTo(greenBox.end)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.value(100.dp)
        }


        createHorizontalChain(greenBox,redBox,chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(constraintSet = constraints,modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenBox")
        )
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("redBox")
        )
    }
}