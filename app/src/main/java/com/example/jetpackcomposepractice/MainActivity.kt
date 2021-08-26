package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val fontFamily = FontFamily(
                Font(R.font.lexend_thin,weight = FontWeight.Thin),
                Font(R.font.lexend_black,weight = FontWeight.Black),
                Font(R.font.lexend_bold,weight = FontWeight.Bold),
                Font(R.font.lexend_extrabold,weight = FontWeight.ExtraBold),
                Font(R.font.lexend_light,weight = FontWeight.Light),
                Font(R.font.lexend_extralight,weight = FontWeight.ExtraLight),
                Font(R.font.lexend_medium,weight = FontWeight.Medium),
                Font(R.font.lexend_regular,weight = FontWeight.Normal),
                Font(R.font.lexend_semibold,weight = FontWeight.SemiBold),
            )

            StylingText(fontFamily,modifier = Modifier)
        }
    }


}

@Composable
private fun ImageCardComposable() {
    val painter = painterResource(id = R.drawable.kermit)
    val title = "This is Kermit in the snow"
    val contentDescription = "This is Kermit in the snow"

    ImageCard(painter = painter, title = title, contentDescription = contentDescription)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsOfModifiers()
}