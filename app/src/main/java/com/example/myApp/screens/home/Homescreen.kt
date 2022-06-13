package com.example.myApp.screens.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myApp.Daten.PuzzleList
import com.example.myApp.Daten.getPuzzles
import com.example.myApp.widgets.PuzzleRow

@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Main Menu", style = MaterialTheme.typography.caption) },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false}) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Row(modifier = Modifier
                                .clickable { navController.navigate(route = "gamescreen")}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(puzzles: List<PuzzleList> = getPuzzles(), navController: NavController) {
    LazyVerticalGrid (
        cells = GridCells.Adaptive(minSize = 120.dp))
    {
        items(items = puzzles) { puzzle ->
            PuzzleRow(puzzle = puzzle) { puzzleId ->
                navController.navigate(route = "detailscreen/$puzzleId")

            }
        }
    }}
