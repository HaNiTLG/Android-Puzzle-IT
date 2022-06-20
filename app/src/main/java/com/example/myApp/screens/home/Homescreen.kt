package com.example.myApp.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.Daten.PuzzleList
import com.example.myApp.Daten.getPuzzles
import com.example.myApp.widgets.PuzzleRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Main Menu", style = MaterialTheme.typography.caption) },
            )
        }
    ) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(puzzles: List<PuzzleList> = getPuzzles(), navController: NavController) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(minSize = 120.dp))
    {
        items(items = puzzles) { puzzle ->
            PuzzleRow(puzzle = puzzle) { puzzleId ->
                navController.navigate(route = "detailscreen/$puzzleId")

            }
        }
    }}
