package com.example.myApp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myApp.screens.detail.DetailScreen
import com.example.myApp.screens.game.*
import com.example.myApp.screens.home.HomeScreen

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController)}
        composable(
            "detailscreen/{puzzleId}",
            arguments = listOf(navArgument("puzzleId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(navController = navController, puzzleId = backStackEntry.arguments?.getString("puzzleId"))
        }
        composable("Level1") { Level1(navController = navController, difficulty = 1) }
        composable("Level2") { Level2(navController = navController, difficulty = 2) }
        composable("Level3") { Level3(navController = navController, difficulty = 3) }
        composable("Level4") { Level4(navController = navController, difficulty = 4) }
        composable("Level5") { Level5(navController = navController, difficulty = 5) }
    }
}