package com.example.myApp.screens.game

import android.media.Image
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myApp.R


@Preview(showBackground = true)
@Composable
fun Gamescreen (navController: NavController = rememberNavController()){
    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "Have Fun",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    ) {
        MainContent()
    }
}

@Composable
fun MainContent() {
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.englischerosen)
                .crossfade(true)
                .build(),
            contentDescription = "Picture of Puzzle",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .graphicsLayer {
                    //translationX = 0.5f
                    //translationY = 0.5f
                    scaleX = 0.5f
                    scaleY = 0.5f
                })

    }
}
@Composable
fun splitImage(): ArrayList<ImageView> {
    val pcs = 9;
    val col = 3; //Spalte
    val rows = 3 //Zeile

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.englischerosen)
            .crossfade(true)
            .build(),
        contentDescription = "Picture of Puzzle",
        contentScale = ContentScale.Crop,
        modifier = Modifier
        .graphicsLayer {
            translationX = 0.4f
            translationY = 0.4f
            rotationY = 53f
            rotationX = 44f
            rotationZ = 23f
            scaleX = 0.4f
            scaleY = 0.5f
        })

    return ArrayList()
}