package com.example.budgetcalculator.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.budgetcalculator.model.BudgetData
import com.example.budgetcalculator.ui.theme.Aztec
import com.example.budgetcalculator.ui.theme.LightGreen1
import com.example.budgetcalculator.ui.theme.MintTulip
import com.example.budgetcalculator.ui.theme.RoseRed

@Composable
fun BudgetDashboard(budgetData: BudgetData) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val dynamicHeight = screenHeight * 0.5f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dynamicHeight)
            .padding(20.dp)
            .border(
                brush = Brush.horizontalGradient(listOf(LightGreen1, RoseRed)),
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
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
                            .padding(8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(color = MintTulip)
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(text = it.toString(), color = Aztec)
                            IconButton(onClick = {
                                budgetData.incomeList.remove(it)
                                budgetData.newIncome.doubleValue -= it
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Cross Button",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
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
                            .padding(8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(color = MintTulip)
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(text = it.toString(), color = Aztec)
                            IconButton(onClick = {
                                budgetData.expenseList.remove(it)
                                budgetData.newExpense.doubleValue -= it
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
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