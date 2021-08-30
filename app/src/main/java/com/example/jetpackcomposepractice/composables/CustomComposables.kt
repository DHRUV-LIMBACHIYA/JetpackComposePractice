package com.example.jetpackcomposepractice.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposepractice.R
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Created by Dhruv Limbachiya on 25-08-2021.
 */


@Composable
fun List() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(5000) {
//            Text(
//                text = "Item $it",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.fillMaxWidth()
//                    .padding(16.dp),
//                textAlign = TextAlign.Center
//            )
//        }

        itemsIndexed(
            listOf("This","is","an","example","of","jetpack","compose","list")
        ){ index,item ->
            Text(
                text = item,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GreetMe(
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    var text by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = {
                    Text(text = "Enter your name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "Hello $text",
                            duration = SnackbarDuration.Short
                        )
                    }
                }) {
                    Text(text = "Pls greet me")
                }
            }
        }
    }
}


@Composable
fun ColorBoxColumn() {
    Column {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }
        ColorBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color.value = it
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(color.value)
        )
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.Red)
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                    )
                )
            }
    )
}


@Composable
fun StylingText() {
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, weight = FontWeight.Thin),
        Font(R.font.lexend_black, weight = FontWeight.Black),
        Font(R.font.lexend_bold, weight = FontWeight.Bold),
        Font(R.font.lexend_extrabold, weight = FontWeight.ExtraBold),
        Font(R.font.lexend_light, weight = FontWeight.Light),
        Font(R.font.lexend_extralight, weight = FontWeight.ExtraLight),
        Font(R.font.lexend_medium, weight = FontWeight.Medium),
        Font(R.font.lexend_regular, weight = FontWeight.Normal),
        Font(R.font.lexend_semibold, weight = FontWeight.SemiBold),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        Color.Green,
                        50.sp,
                        FontWeight.Normal
                    )
                ) {
                    append("J")
                }
                append("etpack")
                withStyle(
                    SpanStyle(
                        Color.Green,
                        50.sp,
                        FontWeight.Normal
                    )
                ) {
                    append("C")
                }
                append("ompose")

            },
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = fontFamily,
            textDecoration = TextDecoration.LineThrough
        )
    }
}

@Composable
fun ImageCard() {
    val painter = painterResource(id = R.drawable.kermit)
    val title = "This is Kermit in the snow"
    val contentDescription = "This is Kermit in the snow"

    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {}

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}


/**
 * Composable demonstrate basic of modifiers.
 * - chaining of modifiers method.
 * - offset vs margin.
 * - padding between views.
 * - Spacer for adding space.
 * - interaction(clickable).
 */
@Composable
fun BasicsOfModifiers() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .border(10.dp, Color.Magenta)
            .padding(10.dp)
            .border(10.dp, Color.Blue)
            .padding(10.dp)
            .border(10.dp, Color.Cyan)
            .padding(10.dp)
    ) {
        Text(text = "hello", modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .clickable { /* NO-OP */ })
        Spacer(modifier = Modifier.height(20.dp))
        Text("world", modifier = Modifier.offset(50.dp, 20.dp))
    }
}

@Composable
fun ColumnComposablePractice() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.Magenta),
        verticalArrangement = Arrangement.SpaceEvenly, // Main Axis
        horizontalAlignment = Alignment.CenterHorizontally // Cross Axis
    ) {
        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 1")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 2")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 3")
        }
    }
}

@Composable
fun RowComposablePractice() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(Color.Green)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,  // Main Axis
        verticalAlignment = Alignment.CenterVertically // Cross Axis
    ) {
        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 4")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 5")
        }

        Button(onClick = { /* NO-OP */ }) {
            Text(text = "Button 6")
        }
    }
}