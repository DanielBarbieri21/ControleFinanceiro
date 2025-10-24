package com.example.controlefinanceiro.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.controlefinanceiro.ui.theme.YellowText

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { 
                Text(
                    text = label,
                    color = YellowText,
                    fontWeight = FontWeight.Medium
                )
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = YellowText,
                unfocusedTextColor = YellowText,
                focusedLabelColor = YellowText,
                unfocusedLabelColor = YellowText,
                cursorColor = YellowText,
                focusedBorderColor = if (isError) Color.Red else YellowText,
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray
            ),
            shape = MaterialTheme.shapes.medium
        )
        if (isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    color: Color = YellowText
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = Color.Gray
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = text,
            color = if (enabled) Color.Black else Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}
