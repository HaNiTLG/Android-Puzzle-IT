package com.example.myApp.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.Daten.PuzzleList
import com.example.myApp.Daten.getPuzzle
import com.example.myApp.widgets.HorizontalScrollableImageView
import com.example.myApp.widgets.PuzzleRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                    }
                 }
            }
         )
         {
        MainContent(puzzle = puzzle, navController)
    }
}

@Composable
fun MainContent(puzzle: PuzzleList, navController: NavController = rememberNavController()) {
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            PuzzleRow(puzzle = puzzle)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            HorizontalScrollableImageView(puzzle = puzzle)
            if (puzzle.title == "Level 1")
                OutlinedButton(onClick = {navController.navigate(route = "Level1")})
                {
                    Text("Start Game - LEVEL 1")
                }
            if (puzzle.title == "Level 2")
                OutlinedButton(onClick = {navController.navigate(route = "Level2")})
                {
                    Text("Start Game - LEVEL 2")
                }
            if (puzzle.title == "Level 3")
                OutlinedButton(onClick = {navController.navigate(route = "Level3")})
                {
                    Text("Start Game - LEVEL 3")
                }
            if (puzzle.title == "Level 4")
                OutlinedButton(onClick = {navController.navigate(route = "Level4")})
                {
                    Text("Start Game - LEVEL 4")
                }
            if (puzzle.title == "Level 5")
                OutlinedButton(onClick = {navController.navigate(route = "Level5")})
                {
                    Text("Start Game - LEVEL 5")
                }
        }
    }
}