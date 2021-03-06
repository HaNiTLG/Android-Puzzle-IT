package com.example.myApp.Daten

import com.example.myApp.R

data class PuzzleList(
    val id: String,
    val title: String,
    val name: String,
    val difficulty: Int,
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
            difficulty = 0,
           images = listOf(R.drawable.level1),
        ),

        PuzzleList(
            id = "tt0416449",
            title = "Level 2",
            name = "Landscape",
            difficulty = 1,
            images = listOf(R.drawable.level2),
        ),

        PuzzleList(
            id = "tt0848228",
            title = "Level 3",
            name = "Waterfall",
            difficulty = 2,
            images = listOf(R.drawable.level3),
        ),

        PuzzleList(
            id = "tt0993846",
            title = "Level 4",
            name = "Computer",
            difficulty = 3,
            images = listOf(R.drawable.level4),
        ),

        PuzzleList(
            id = "tt0816692",
            title = "Level 5",
            name = "Mountains",
            difficulty = 4,
            images = listOf(R.drawable.level5),
        ),
        )
}