package com.example.myApp.screens.favorit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.myApp.Daten.getPuzzles
import com.example.myApp.widgets.PuzzleRow

@Preview(showBackground = true)
@Composable
fun FavoritScreen(navController: NavController = rememberNavController()) {
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

                    Text(text = "Favoriten",
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
fun MainContent(puzzles: List<PuzzleList> = getPuzzles()) {
    LazyColumn {
        items(items = puzzles.slice(1..3)) { puzzle ->
            PuzzleRow(puzzle = puzzle)
        }
    }
}
