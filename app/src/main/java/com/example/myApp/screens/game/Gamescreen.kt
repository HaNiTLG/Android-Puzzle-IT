package com.example.myApp.screens.game



import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.LocaleDisplayNames
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.R
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable
import java.util.*
import kotlin.math.roundToInt



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun Gamescreen (navController: NavController = rememberNavController(), difficulty: Int = 0, imageID: Int = 0) {
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
        //MainContent(difficulty, imageID)
        VerticalGrid()
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(difficulty: Int, imageID: Int) {
    var colum = 3 //Leicht
    if (difficulty == 1)  { //Mittel
        colum = 4
    } else if (difficulty == 2) { //Schwer
        colum = 5
    }
    val list = (1..colum*colum).map { it.toString() } //9,16,25
    val configuration = LocalConfiguration.current
    val imageHeight = (configuration.screenHeightDp.dp - (101-(5-colum)).dp) / colum

    val b: Bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.rosen)
    val splittedBitmap = splitImage(b,colum*colum)
    //3 = 99, 4 = 100, 5 = 101
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(colum), //Anzahl der Spalten
            modifier = Modifier,
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            )
        ){
            itemsIndexed(list) { it, list ->
                Card(
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth()
                        .clickable {
                            move(splittedBitmap, it)
                        }
                        .fillMaxHeight(),
                    elevation = 8.dp,
                ) {
                    Image(bitmap = splittedBitmap[it].asImageBitmap(), contentDescription = "Rosen")
                }
            }
        }
    }
}

private fun move(splittedBitmap:ArrayList<Bitmap>,imgID:Int) {
    Collections.swap(splittedBitmap, imgID, 6)
}

@Composable
private fun VerticalGrid(){
    val data = remember {mutableStateOf(List(100) { "item $it" })}
    val state = rememberReorderableLazyGridState(onMove = {from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    })
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        state = state.gridState,
        contentPadding = PaddingValues(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .reorderable(state)
            .detectReorderAfterLongPress(state)
    ) {
        items(data.value, { it }) { item ->
            ReorderableItem(state, key = item, defaultDraggingModifier = Modifier) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .size(100.dp)
                ) {
                    Text(text = item,
                        modifier = Modifier.detectReorderAfterLongPress(state))
                }
            }
        }
    }
}


@Composable
fun movePictures(pic1: Int, pic2: Int, listItem: LocaleDisplayNames.UiListItem) {
    val offsetX = remember { mutableStateOf( 0f)}
    val offsetY = remember { mutableStateOf( 0f)}

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.Blue),
    )
}

fun splitImage(image: Bitmap, chunkNumbers: Int): ArrayList<Bitmap>{

        //For the number of rows and columns of the grid to be displayed
        val rows: Int
        val cols: Int

        //For height and width of the small image chunks
        val chunkHeight: Int
        val chunkWidth: Int

        //To store all the small image chunks in bitmap format in this list
        val chunkedImages = ArrayList<Bitmap>(chunkNumbers)

        //Getting the scaled bitmap of the source image

        //val bitmap = image.bitmap
        val scaledBitmap = Bitmap.createScaledBitmap(image, image.width, image.height, true)
        cols = Math.sqrt(chunkNumbers.toDouble()).toInt()
        rows = cols
        chunkHeight = image.height / rows
        chunkWidth = image.width / cols

        //xCoord and yCoord are the pixel positions of the image chunks
        var yCoord = 0
        for (x in 0 until rows) {
            var xCoord = 0
            for (y in 0 until cols) {
                chunkedImages.add(
                    Bitmap.createBitmap(
                        scaledBitmap,
                        xCoord,
                        yCoord,
                        chunkWidth,
                        chunkHeight
                    )
                )
                xCoord += chunkWidth
            }
            yCoord += chunkHeight
        }
    return chunkedImages
}
