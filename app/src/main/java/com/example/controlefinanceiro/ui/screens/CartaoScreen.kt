package com.example.controlefinanceiro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.controlefinanceiro.ui.components.ActionButton
import com.example.controlefinanceiro.ui.components.FormField
import com.example.controlefinanceiro.ui.theme.BlackBackground
import com.example.controlefinanceiro.ui.theme.RedExpense
import com.example.controlefinanceiro.ui.theme.YellowText
import com.example.controlefinanceiro.utils.ValidationUtils
import com.example.controlefinanceiro.viewmodel.CartaoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaoScreen(
    onBack: () -> Unit,
    viewModel: CartaoViewModel = hiltViewModel()
) {
    var descricao by remember { mutableStateOf("") }
    var valorTotal by remember { mutableStateOf("") }
    var parcelas by remember { mutableStateOf("") }
    var dataVencimento by remember { mutableStateOf("") }
    
    var descricaoError by remember { mutableStateOf(false) }
    var valorError by remember { mutableStateOf(false) }
    var parcelasError by remember { mutableStateOf(false) }
    var dataError by remember { mutableStateOf(false) }
    
    var isLoading by remember { mutableStateOf(false) }

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
                FormField(
                    value = descricao,
                    onValueChange = { 
                        descricao = it
                        descricaoError = false
                    },
                    label = "Descrição da Compra",
                    isError = descricaoError,
                    errorMessage = ValidationUtils.getDescriptionError(descricao) ?: ""
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                FormField(
                    value = valorTotal,
                    onValueChange = { 
                        valorTotal = it
                        valorError = false
                    },
                    label = "Valor Total (R$)",
                    isError = valorError,
                    errorMessage = ValidationUtils.getAmountError(valorTotal) ?: ""
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                FormField(
                    value = parcelas,
                    onValueChange = { 
                        parcelas = it
                        parcelasError = false
                    },
                    label = "Número de Parcelas",
                    isError = parcelasError,
                    errorMessage = ValidationUtils.getInstallmentsError(parcelas) ?: ""
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                FormField(
                    value = dataVencimento,
                    onValueChange = { 
                        dataVencimento = it
                        dataError = false
                    },
                    label = "Data de Vencimento (dd/MM/yyyy)",
                    isError = dataError,
                    errorMessage = ValidationUtils.getDateError(dataVencimento) ?: ""
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                ActionButton(
                    text = if (isLoading) "Salvando..." else "Salvar Compra Parcelada",
                    onClick = {
                        // Validação usando utilitários
                        descricaoError = !ValidationUtils.isValidDescription(descricao)
                        valorError = !ValidationUtils.isValidAmount(valorTotal)
                        parcelasError = !ValidationUtils.isValidInstallments(parcelas)
                        dataError = !ValidationUtils.isValidDate(dataVencimento)
                        
                        if (!descricaoError && !valorError && !parcelasError && !dataError) {
                            isLoading = true
                            viewModel.insert(
                                descricao,
                                valorTotal.toDoubleOrNull() ?: 0.0,
                                parcelas.toIntOrNull() ?: 1,
                                dataVencimento
                            )
                            onBack()
                        }
                    },
                    enabled = !isLoading,
                    color = RedExpense
                )
            }
        }
    )
}
