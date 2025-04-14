package com.example.controlefinanceiro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controlefinanceiro.ui.theme.BlackBackground
import com.example.controlefinanceiro.ui.theme.GreenIncome
import com.example.controlefinanceiro.ui.theme.YellowText
import com.example.controlefinanceiro.viewmodel.ReceitaViewModel

@OptIn(ExperimentalMaterial3Api::class) // Adicionado para suprimir os avisos
@Composable
fun ReceitaScreen(viewModel: ReceitaViewModel, onBack: () -> Unit) {
    var descricao by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Receita", color = YellowText) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("<", color = YellowText)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BlackBackground)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackBackground)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição", color = YellowText) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = YellowText,
                        unfocusedTextColor = YellowText,
                        focusedLabelColor = YellowText,
                        unfocusedLabelColor = YellowText,
                        cursorColor = YellowText
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = valor,
                    onValueChange = { valor = it },
                    label = { Text("Valor", color = YellowText) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = YellowText,
                        unfocusedTextColor = YellowText,
                        focusedLabelColor = YellowText,
                        unfocusedLabelColor = YellowText,
                        cursorColor = YellowText
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = data,
                    onValueChange = { data = it },
                    label = { Text("Data (dd/MM/yyyy)", color = YellowText) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = YellowText,
                        unfocusedTextColor = YellowText,
                        focusedLabelColor = YellowText,
                        unfocusedLabelColor = YellowText,
                        cursorColor = YellowText
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (descricao.isNotBlank() && valor.isNotBlank() && data.isNotBlank()) {
                            viewModel.insert(descricao, valor.toDoubleOrNull() ?: 0.0, data)
                            onBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenIncome)
                ) {
                    Text("Salvar", color = BlackBackground)
                }
            }
        }
    )
}