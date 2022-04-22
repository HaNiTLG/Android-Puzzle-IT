package com.example.myApp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myApp.screens.detail.DetailScreen
import com.example.myApp.screens.favorit.FavoritScreen
import com.example.myApp.screens.home.HomeScreen

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController)}
        composable(
            "detailscreen/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable("favoritscreen") { FavoritScreen(navController = navController)}
    }
}