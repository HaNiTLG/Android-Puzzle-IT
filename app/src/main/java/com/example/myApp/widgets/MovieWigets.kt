package com.example.myApp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myApp.Daten.Movie
import com.example.myApp.Daten.getMovies

@Composable
fun MovieRow(movie: Movie, Click: (String) -> Unit = {}) {
    var state by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { Click(movie.id) }
            .defaultMinSize(minHeight = 130.dp),

        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp),
            ) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[0])
                    .crossfade(true)
                    .build(),
                    contentDescription = "movie picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = "Release: ${movie.year}",
                    style = MaterialTheme.typography.subtitle1
                )
                AnimatedVisibility(visible = state) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp,8.dp,0.dp,0.dp)
                    ) {
                        Text(
                            text = "Plot: ${movie.plot}",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Divider(Modifier.padding(5.dp), color = Color.Gray, thickness = 1.dp )
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
                IconButton(onClick = { state = !state }) {
                    if (!state) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "More")
                    } else {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Less")
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 4.dp) {
                AsyncImage(model = image, contentDescription = movie.title )
            }
        }
    }
}

