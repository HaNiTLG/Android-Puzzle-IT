package com.example.myApp.screens.game



import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.Daten.PuzzleList
import com.example.myApp.Daten.getPuzzle
import com.example.myApp.R
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorder
import org.burnoutcrew.reorderable.rememberReorderableLazyGridState
import org.burnoutcrew.reorderable.reorderable



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Level1 (puzzleId: String? = "tt0499549", navController: NavController = rememberNavController(), difficulty: Int = 0, imageID: Int = 0) {
    val puzzle = getPuzzle(puzzleId = puzzleId)
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

        MainContentLevel1(puzzle, difficulty, imageID, navController)
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainContentLevel1(puzzle: PuzzleList, difficulty: Int, imageID: Int, navController: NavController) {
    var colum = 3

    if (difficulty == 2) {
        colum = 3
    }
    if (difficulty == 3) {
        colum = 3
    }
    if (difficulty == 4) {
        colum = 4
    }
    if (difficulty == 5) {
        colum = 5
    }

    val b: Bitmap =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.level1)
    val splittedBitmap = splitImageLevel1(b,colum*colum)
    val data = remember {mutableStateOf(List(colum*colum) { it })} //Index of Grid fields, ordered

    val state = rememberReorderableLazyGridState(onMove = {from, to ->
        data.value = data.value.toMutableList().apply {
            swapLevel1(to.index, from.index)
        }
    })

    if (isGameOverLevel1(data.value) && statikLevel1.count == 0) {// If Sorted
        data.value = data.value.toMutableList().apply { //Randomise
            shuffle()
        }
    }

    statikLevel1() //For Closing Game

    if (isGameOverLevel1(data.value) && statikLevel1.count > 0) {
        popUpWindowLevel1(navController)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(colum),
            state = state.gridState,
            contentPadding = PaddingValues(horizontal = 2.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            modifier = Modifier
                .reorderable(state)
                .detectReorder(state) //hold on the grid element for Movement
        ) {
            items(data.value, { it }) { item ->
                ReorderableItem(state, key = item, defaultDraggingModifier = Modifier) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    ) {
                        Image(
                            bitmap = splittedBitmap[item].asImageBitmap(),
                            contentDescription = "Rosen",
                            modifier = Modifier.detectReorder(state)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun popUpWindowLevel1(navController: NavController){
    val open = remember { mutableStateOf(false)}

    AlertDialog(
        onDismissRequest = {
            open.value = false
        }, title = {
            Text(text = "GEWONNEN!")
        },
        confirmButton = {
            Button(
                onClick = {
                    open.value = true
                    navController.navigate(route = "homescreen")
                }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    open.value = true
                }) {
                Text("Cancel")

            }
        }
    )
}

class statikLevel1() {
    companion object {
        var count:Int=0;
    }
    init {
        count++
    }
}

fun isGameOverLevel1(data: List<Int>): Boolean {
    if (data == data.toMutableList().sorted()) {// If Sorted
        return true
    }
    return false
}

fun <T> MutableList<T>.swapLevel1(index1: Int, index2: Int){
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun splitImageLevel1(image: Bitmap, chunkNumbers: Int): ArrayList<Bitmap>{

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