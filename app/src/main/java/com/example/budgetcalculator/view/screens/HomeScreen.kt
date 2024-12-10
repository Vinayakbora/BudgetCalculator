package com.example.budgetcalculator.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.example.budgetcalculator.ui.theme.RoseRed
import com.example.budgetcalculator.ui.theme.TextWhite

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
        paddingValues.calculateTopPadding()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 60.dp)
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
                color = Color.Black,
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
                focusedTextColor = Color.Black,
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
                color = Color.Black,
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
                focusedTextColor = Color.Black,
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

@Composable
fun BudgetDashboard(budgetData: BudgetData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(20.dp)
            .border(
                brush = Brush.horizontalGradient(listOf(LightGreen1, RoseRed)),
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                budgetData.incomeList.forEach {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                        ) {
                            Text(text = it.toString())
                            IconButton(onClick = {
                                budgetData.incomeList.remove(it)
                                budgetData.newIncome.doubleValue -= it
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Cross Button",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .background(brush = Brush.horizontalGradient(listOf(LightGreen1, RoseRed)))
                    .width(2.dp)
            ) {}
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                budgetData.expenseList.forEach {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                        ) {
                            Text(text = it.toString())
                            IconButton(onClick = {
                                budgetData.expenseList.remove(it)
                                budgetData.newExpense.doubleValue -= it
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Cross Button",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BudgetResult(
    onClear: () -> Unit,
    budgetData: BudgetData,
) {
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
            colors = CardDefaults.cardColors(MintTulip, Aztec, MintTulip, Aztec)

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
                        text = "₹${budgetData.newIncome.doubleValue}",
                        fontSize = 20.sp,
                        color = Juniper,
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
                        text = "-₹${budgetData.newExpense.doubleValue}",
                        fontSize = 20.sp,
                        color = Juniper,
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
                        text = "₹${budgetData.newIncome.doubleValue - budgetData.newExpense.doubleValue}",
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp)
                    )
                }

                Box(modifier = Modifier.padding(40.dp, 20.dp, 40.dp, 20.dp)) {
                    Button(
                        onClick = onClear,
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = TextWhite,
                            disabledContainerColor = Color.Black,
                            disabledContentColor = TextWhite
                        )
                    ) {
                        Text(text = "Clear All")
                    }
                }
            }
        }
    }
}

