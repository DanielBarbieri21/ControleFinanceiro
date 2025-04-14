package com.example.controlefinanceiro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controlefinanceiro.ui.theme.BlackBackground
import com.example.controlefinanceiro.ui.theme.RedExpense
import com.example.controlefinanceiro.ui.theme.YellowText
import com.example.controlefinanceiro.viewmodel.CartaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaoScreen(viewModel: CartaoViewModel, onBack: () -> Unit) {
    var descricao by remember { mutableStateOf("") }
    var valorTotal by remember { mutableStateOf("") }
    var parcelas by remember { mutableStateOf("") }
    var dataVencimento by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Cartão", color = YellowText) },
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
                    value = valorTotal,
                    onValueChange = { valorTotal = it },
                    label = { Text("Valor Total", color = YellowText) },
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
                    value = parcelas,
                    onValueChange = { parcelas = it },
                    label = { Text("Número de Parcelas", color = YellowText) },
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
                    value = dataVencimento,
                    onValueChange = { dataVencimento = it },
                    label = { Text("Data de Vencimento (dd/MM/yyyy)", color = YellowText) },
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
                        if (descricao.isNotBlank() && valorTotal.isNotBlank() && parcelas.isNotBlank() && dataVencimento.isNotBlank()) {
                            viewModel.insert(
                                descricao,
                                valorTotal.toDoubleOrNull() ?: 0.0,
                                parcelas.toIntOrNull() ?: 1,
                                dataVencimento
                            )
                            onBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = RedExpense)
                ) {
                    Text("Salvar", color = BlackBackground)
                }
            }
        }
    )
}
