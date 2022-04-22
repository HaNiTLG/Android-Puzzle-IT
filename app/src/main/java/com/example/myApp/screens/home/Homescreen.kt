package com.example.myApp.screens.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.myApp.Daten.Movie
import com.example.myApp.Daten.getMovies
import com.example.myApp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies", style = MaterialTheme.typography.caption) },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false}) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Row(modifier = Modifier
                                .clickable { navController.navigate(route = "favoritscreen")}
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

@Composable
fun MainContent(movies: List<Movie> = getMovies(), navController: NavController) {
    LazyColumn {
        items(items = movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(route = "detailscreen/$movieId")
            }
        }
    }
}
