package com.example.budgetcalculator.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
import com.example.budgetcalculator.model.BudgetData
import com.example.budgetcalculator.ui.theme.APP_NAME
import com.example.budgetcalculator.ui.theme.Aztec
import com.example.budgetcalculator.ui.theme.EMPTY_STRING
import com.example.budgetcalculator.ui.theme.Juniper
import com.example.budgetcalculator.ui.theme.LightGreen1
import com.example.budgetcalculator.ui.theme.LightGrey
import com.example.budgetcalculator.ui.theme.LightRed
import com.example.budgetcalculator.ui.theme.MintTulip
import com.example.budgetcalculator.view.widgets.BudgetDashboard
import com.example.budgetcalculator.view.widgets.BudgetResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val budgetData by remember { mutableStateOf(BudgetData()) }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = APP_NAME,
                        color = Juniper,
                        style = TextStyle(
                            fontSize = 30.sp, color = Color.Black, fontWeight = FontWeight.Bold
                        ),
                    )
                }
            },
            colors = TopAppBarColors(
                containerColor = Aztec,
                scrolledContainerColor = Aztec,
                navigationIconContentColor = Aztec,
                titleContentColor = Juniper,
                actionIconContentColor = Aztec
            )
        )
    }) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .background(color = Aztec)
        ) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                IncomeInput(budgetData = budgetData)
                Spacer(modifier = Modifier.weight(1f))
                ExpenseInput(budgetData = budgetData)
            }
            BudgetDashboard(budgetData = budgetData)
            Spacer(modifier = Modifier.weight(1f))
            BudgetResult(
                onClear = {
                    budgetData.newIncome.doubleValue = 0.0
                    budgetData.newExpense.doubleValue = 0.0
                    budgetData.incomeList.clear()
                    budgetData.expenseList.clear()
                },
                budgetData = budgetData
            )
        }
    }
}

@Composable
fun IncomeInput(budgetData: BudgetData) {
    var incomeText by remember { mutableStateOf(EMPTY_STRING) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = incomeText,
            onValueChange = { text ->
                incomeText = text
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MintTulip,
                fontWeight = FontWeight.Bold
            ),
            label = {
                Text(
                    text = "Income",
                    textAlign = TextAlign.Start,
                    color = LightGreen1
                )
            },
            modifier = Modifier
                .padding(10.dp)
                .width(150.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MintTulip,
                cursorColor = LightGreen1,
                focusedBorderColor = LightGreen1,
                unfocusedBorderColor = LightGreen1
            )
        )
        Button(
            onClick = {
                val newIncomeValue = incomeText.toDoubleOrNull() ?: 0.0
                budgetData.newIncome.doubleValue += newIncomeValue
                budgetData.incomeList.add(newIncomeValue)
                incomeText = EMPTY_STRING
                keyboardController?.hide()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen1,
                contentColor = Aztec,
                disabledContainerColor = LightGrey,
                disabledContentColor = Color.Black
            )
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun ExpenseInput(budgetData: BudgetData) {
    var expenseText by remember { mutableStateOf(EMPTY_STRING) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = expenseText,
            onValueChange = { text ->
                expenseText = text
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MintTulip,
                fontWeight = FontWeight.Bold
            ),
            label = {
                Text(
                    text = "Expense",
                    textAlign = TextAlign.Start,
                    color = LightRed
                )
            },
            modifier = Modifier
                .padding(10.dp)
                .width(150.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MintTulip,
                cursorColor = LightRed,
                focusedBorderColor = LightRed,
                unfocusedBorderColor = LightRed
            )
        )
        Button(
            onClick = {
                val newExpenseValue = expenseText.toDoubleOrNull() ?: 0.0
                budgetData.newExpense.doubleValue += newExpenseValue
                budgetData.expenseList.add(newExpenseValue)
                expenseText = EMPTY_STRING
                keyboardController?.hide()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(LightRed, Aztec, LightGrey, Aztec)
        ) {
            Text(text = "Add")
        }
    }
}




