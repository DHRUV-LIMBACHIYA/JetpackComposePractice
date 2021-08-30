package com.example.jetpackcomposepractice

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.material.*
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

/**
 * Created by Dhruv Limbachiya on 27-08-2021.
 */

var counter = 0

@Composable
fun ComposableWithSideEffectHandler() {

    // SideEffect block will execute on every successful recomposition.
    SideEffect {
        counter++
    }

    Button(onClick = { /*TODO*/ }) {
        Text(text = "Click Me!")
    }
}

@Composable
fun ComposableWithDisposableEffectHandler(backPressedDispatcher: OnBackPressedDispatcher) {

    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                /* NO OP */
            }
        }
    }

    /**
     * DisposableEffect will dispose or clean up resource on the beginning of every recomposition.
     * If a key changes, the DisposableEffect must dispose its current effect and reset by calling effect again
     * @param key1 = a value that must be clean up if there is change is key.
     */
    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

    Button(onClick = { /*TODO*/ }) {
        Text(text = "Click Me!")
    }
}

@Composable
fun ComposableWithLaunchedEffect(
    scaffoldState: ScaffoldState
){
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        var counter by remember {
            mutableStateOf(0)
        }

        if(counter % 3 == 0 && counter > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Hello there!",
                    duration = SnackbarDuration.Short
                )
            }
        }

        Button(onClick = { counter++ }) {
            Text(text = "Clicks: $counter")
        }
    }
}

@Composable
fun ComposableWithProduceStateEffect(
    scaffoldState: ScaffoldState
){
    Scaffold(
        scaffoldState = scaffoldState
    ) {

        /**
         * Produce state run the asynchronous code and  return observable state
         */
        val counter = produceState(initialValue = 0) {
            delay(5000L)
            value = 5
        }

        if(counter.value % 3 == 0 && counter.value > 0) {
            LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Hello there!",
                    duration = SnackbarDuration.Short
                )
            }
        }

        Button(onClick = {  }) {
            Text(text = "Clicks: ${counter.value}")
        }
    }
}