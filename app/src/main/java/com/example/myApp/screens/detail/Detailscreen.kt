package com.example.myApp.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.Daten.PuzzleList
import com.example.myApp.Daten.getPuzzle
import com.example.myApp.widgets.HorizontalScrollableImageView
import com.example.myApp.widgets.PuzzleRow

@Preview(showBackground = true)
@Composable
fun DetailScreen(puzzleId: String? = "tt0499549", navController: NavController = rememberNavController()) {
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

                    Text(text = puzzle.title,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    ) {
        MainContent(puzzle = puzzle)
    }
}




@Composable
fun MainContent(puzzle: PuzzleList) {
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            PuzzleRow(puzzle = puzzle)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            HorizontalScrollableImageView(puzzle = puzzle)
        }
    }
}