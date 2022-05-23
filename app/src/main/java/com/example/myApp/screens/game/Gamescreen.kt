package com.example.myApp.screens.game

import android.media.Image
import android.media.MediaDescription
import android.widget.ImageView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent() {
    val list = (1..9).map { it.toString() }
    //9; 16; 25
    val configuration = LocalConfiguration.current

    val colum = 3
    val imageHeight = (configuration.screenHeightDp.dp - (101-(5-colum)).dp) / colum
    //3 = 99
    //4 = 100
    //5 = 101

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(colum), //Anzahl der Spalten
            modifier = Modifier,
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                itemsIndexed(list) { index, list ->
                    Card(
                        //backgroundColor = Color.Red,
                        modifier = Modifier
                            .padding(1.dp)
                            .fillMaxWidth()
                            .height(imageHeight),
                        elevation = 8.dp,
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.drawable.englischerosen)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Picture of Puzzle",
                            //contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .graphicsLayer {
                                    //translationX = 0.5f
                                    //translationY = 0.5f
                                    //scaleX = 0.5f
                                    //scaleY = 0.5f
                                })

/*                        Text(
                            text = "${index+1}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxHeight()

                        )*/
                    }
                }
            }
        )
    }
}

@Composable
fun splitImage(image: Image,
pcs: Int ): ArrayList<ImageView> {



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
@Composable
fun imageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp

    ) {
        Box(modifier = Modifier
            .height(200.dp))
    }

}