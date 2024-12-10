package com.example.budgetcalculator.utils

sealed class Routes(val route: String) {

    object HomeScreen : Routes("Home")

}
