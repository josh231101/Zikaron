package com.example.zikaron.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavManager() {
    var navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        // Definiendo rutas
        composable(route = "Home") {
            HomeView(navController)
        }
        composable(route = "Products") {
            ProductsView(navController)
        }
        composable(route = "LogIn") {
            LoginView(navController)
        }
        composable(route = "Profile") {
            ProfileView(navController)
        }
    }
}