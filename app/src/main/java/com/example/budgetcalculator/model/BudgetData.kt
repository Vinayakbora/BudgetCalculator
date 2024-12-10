package com.example.budgetcalculator.model

import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf

data class BudgetData(
    var newIncome: MutableDoubleState = mutableDoubleStateOf(0.0),
    var newExpense: MutableDoubleState = mutableDoubleStateOf(0.0),
    var incomeList: MutableList<Double> = mutableStateListOf(),
    var expenseList: MutableList<Double> = mutableStateListOf()
)