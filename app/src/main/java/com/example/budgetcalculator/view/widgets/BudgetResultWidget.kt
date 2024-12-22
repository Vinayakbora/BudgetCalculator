package com.example.budgetcalculator.view.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetcalculator.model.BudgetData
import com.example.budgetcalculator.ui.theme.Aztec
import com.example.budgetcalculator.ui.theme.Juniper
import com.example.budgetcalculator.ui.theme.MintTulip
import com.example.budgetcalculator.ui.theme.TextWhite

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
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                    )
                    Text(
                        text = "-₹${budgetData.newExpense.doubleValue}",
                        fontSize = 20.sp,
                        color = Juniper,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
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
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                    )
                    Text(
                        text = "₹${budgetData.newIncome.doubleValue - budgetData.newExpense.doubleValue}",
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
                    )
                }

                Box(modifier = Modifier.padding(40.dp, 10.dp, 40.dp, 10.dp)) {
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
