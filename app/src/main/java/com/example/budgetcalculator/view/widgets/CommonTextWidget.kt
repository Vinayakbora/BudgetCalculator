package com.example.budgetcalculator.view.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.budgetcalculator.ui.theme.DarkGrey

@Composable
fun CommonTextWidget(
    value: String,
    modifier: Modifier,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color? = null,
) {
    Text(
        text = value,
        modifier = modifier,
        fontSize = fontSize,
        color = color ?: DarkGrey,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = fontWeight,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
        )
    )
}