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
import com.example.myApp.Daten.Movie
import com.example.myApp.Daten.getMovie
import com.example.myApp.widgets.HorizontalScrollableImageView
import com.example.myApp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun DetailScreen(movieId: String? = "tt0499549", navController: NavController = rememberNavController()) {
    val movie = getMovie(movieId = movieId)
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

                    Text(text = movie.title,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    ) {
        MainContent(movie = movie)
    }
}




@Composable
fun MainContent(movie: Movie) {
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column {
            MovieRow(movie = movie)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            HorizontalScrollableImageView(movie = movie)
        }
    }
}