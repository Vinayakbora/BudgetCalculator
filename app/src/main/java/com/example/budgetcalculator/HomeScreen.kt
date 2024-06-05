package com.example.budgetcalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetcalculator.ui.theme.BlueGrotto
import com.example.budgetcalculator.ui.theme.LightGreen1
import com.example.budgetcalculator.ui.theme.LightGrey
import com.example.budgetcalculator.ui.theme.RoseRed
import com.example.budgetcalculator.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var totalIncome by remember { mutableDoubleStateOf(0.0) }
    var totalExpense by remember { mutableDoubleStateOf(0.0) }


    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = APP_NAME,
                    style = TextStyle(
                        fontSize = 30.sp, color = BlueGrotto, fontWeight = FontWeight.Bold
                    ),
                )
            }
        })
    }) { paddingValues ->
        paddingValues.calculateTopPadding()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 100.dp)
        ) {
            IncomeInput(onIncomeChange = { totalIncome = it })
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseInput(onExpenseChange = { totalExpense = it })
            Spacer(modifier = Modifier.height(16.dp))
            BudgetResult(
                totalIncome,
                totalExpense,
                onIncomeChange = { totalIncome = it },
                onExpenseChange = { totalExpense = it })

        }
    }
}

@Composable
fun IncomeInput(onIncomeChange: (Double) -> Unit) {
    var incomeText by remember { mutableStateOf(EMPTY_STRING) }
    var income by remember { mutableDoubleStateOf(0.0) }
    val keyboardController = LocalSoftwareKeyboardController.current


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = incomeText,
            onValueChange = { text ->
                incomeText = text
            },
            label = { Text("Income", color = LightGreen1) },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .width(200.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                cursorColor = LightGreen1,
                focusedBorderColor = LightGreen1,
                unfocusedBorderColor = Color.Black
            )
        )
        Button(
            onClick = {
                income = incomeText.toDoubleOrNull()?.plus(income) ?: 0.0
                onIncomeChange(income)
                incomeText = EMPTY_STRING
                keyboardController?.hide()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(LightGreen1, TextWhite, LightGrey, Color.Black)
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun ExpenseInput(onExpenseChange: (Double) -> Unit) {
    var expenseText by remember { mutableStateOf(EMPTY_STRING) }
    var expense by remember { mutableDoubleStateOf(0.0) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = expenseText,
            onValueChange = { text ->
                expenseText = text
            },
            label = { Text("Expense", color = RoseRed) },
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .width(200.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                cursorColor = RoseRed,
                focusedBorderColor = RoseRed,
                unfocusedBorderColor = Color.Black
            )
        )
        Button(
            onClick = {
                expense = expenseText.toDoubleOrNull()?.plus(expense) ?: 0.0
                onExpenseChange(expense)
                expenseText = EMPTY_STRING
                keyboardController?.hide()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(RoseRed, TextWhite, LightGrey, Color.Black)
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun BudgetResult(
    totalIncome: Double,
    totalExpense: Double,
    onIncomeChange: (Double) -> Unit,
    onExpenseChange: (Double) -> Unit,
) {
    val budget = totalIncome - totalExpense

    Box(
        contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(TextWhite, Color.Black, TextWhite, Color.Black)

        ) {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Income: ",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                    Text(
                        text = "₹$totalIncome",
                        fontSize = 20.sp,
                        color = LightGreen1,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Expense: ",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                    Text(
                        text = "₹$totalExpense",
                        fontSize = 20.sp,
                        color = RoseRed,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Budget: ",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                    Text(
                        text = "₹$budget",
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                }

                Box(modifier = Modifier.padding(40.dp, 20.dp, 40.dp, 20.dp)) {
                    Button(
                        onClick = {
                            onIncomeChange(0.0)
                            onExpenseChange(0.0)
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Black,
                            TextWhite,
                            Color.Black,
                            TextWhite
                        )
                    ) {
                        Text(text = "Clear All")
                    }
                }
            }
        }
    }
}

