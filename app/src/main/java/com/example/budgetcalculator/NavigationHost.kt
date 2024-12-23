package com.example.budgetcalculator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetcalculator.utils.Routes
import com.example.budgetcalculator.view.screens.HomeScreen

@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route) {
            HomeScreen()
        }
    }

}