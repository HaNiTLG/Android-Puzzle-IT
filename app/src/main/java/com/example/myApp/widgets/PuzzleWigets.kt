package com.example.myApp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myApp.Daten.PuzzleList

@Composable
fun PuzzleRow(puzzle: PuzzleList, Click: (String) -> Unit = {}) {
    var state by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { Click(puzzle.id) }
            .defaultMinSize(minHeight = 10.dp),

        shape = RoundedCornerShape(corner = CornerSize(25.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .padding(0.dp),
            ) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(puzzle.images[0])
                    .crossfade(true)
                    .build(),
                    contentDescription = "movie picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RectangleShape)
                )
            }
            Column {
                AnimatedVisibility(visible = state) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp,8.dp,0.dp,0.dp)
                    ) {
                        Text(
                            text = puzzle.title,
                            style = MaterialTheme.typography.subtitle2
                        )
                        Divider(Modifier.padding(5.dp), color = Color.Gray, thickness = 1.dp )
                        Text(
                            text = "Name: ${puzzle.name}",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            text = "Difficulty: ${puzzle.difficulty}",
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }
            }
        }
    }
}

