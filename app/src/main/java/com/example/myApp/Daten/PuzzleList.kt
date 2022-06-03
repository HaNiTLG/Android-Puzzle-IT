package com.example.myApp.Daten
data class PuzzleList (
    val id: String,
    val title: String,
    val name: String,
    val difficulty: String,
    val images: List<String>)

fun getPuzzle(puzzleId: String?) : PuzzleList {
    return getPuzzles().filter { puzzle -> puzzle.id == puzzleId }[0]
}

fun getPuzzles(): List<PuzzleList> {
    return listOf(
        PuzzleList(id = "tt0499549",
            title = "Level 1",
            name = "Simple flowers",
            difficulty = "Very Easy",
           images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
        ),

        PuzzleList(id = "tt0416449",
            title = "Level 2",
            name = "Landscape",
            difficulty = "Easy",
            images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg"),
        ),

        PuzzleList(id = "tt0848228",
            title = "Level 3",
            name = "Waterfall",
            difficulty = "Normal",
            images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"),
        ),

        PuzzleList(id = "tt0993846",
            title = "Level 4",
            name = "Computer",
            difficulty = "Hard",
            images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"),
        ),

        PuzzleList(id = "tt0816692",
            title = "Level 5",
            name = "Mountains",
            difficulty = "Impossible",
            images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg"),
        ),
        )
}