package com.example.myApp.Daten

import com.example.myApp.R

data class PuzzleList(
    val id: String,
    val title: String,
    val name: String,
    val difficulty: String,
    val images: List<Int>
)

fun getPuzzle(puzzleId: String?) : PuzzleList {
    return getPuzzles().filter { puzzle -> puzzle.id == puzzleId }[0]
}

fun getPuzzles(): List<PuzzleList> {
    return listOf(
        PuzzleList(
            id = "tt0499549",
            title = "Level 1",
            name = "Simple flowers",
            difficulty = "Very Easy",
           images = listOf(R.drawable.rosen),
        ),

        PuzzleList(
            id = "tt0416449",
            title = "Level 2",
            name = "Landscape",
            difficulty = "Easy",
            images = listOf(R.drawable.rosen),
        ),

        PuzzleList(
            id = "tt0848228",
            title = "Level 3",
            name = "Waterfall",
            difficulty = "Normal",
            images = listOf(R.drawable.rosen),
        ),

        PuzzleList(
            id = "tt0993846",
            title = "Level 4",
            name = "Computer",
            difficulty = "Hard",
            images = listOf(R.drawable.rosen),
        ),

        PuzzleList(
            id = "tt0816692",
            title = "Level 5",
            name = "Mountains",
            difficulty = "Impossible",
            images = listOf(R.drawable.rosen),
        ),
        )
}