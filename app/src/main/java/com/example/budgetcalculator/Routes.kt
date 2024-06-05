package com.example.budgetcalculator

sealed class Routes(val route: String) {

    object HomeScreen : Routes("Home")

}
