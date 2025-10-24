package com.example.controlefinanceiro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.controlefinanceiro.ui.components.FinanceCard
import com.example.controlefinanceiro.ui.theme.BlackBackground
import com.example.controlefinanceiro.ui.theme.GreenIncome
import com.example.controlefinanceiro.ui.theme.RedExpense
import com.example.controlefinanceiro.ui.theme.YellowText
import com.example.controlefinanceiro.viewmodel.CartaoViewModel
import com.example.controlefinanceiro.viewmodel.ContaViewModel
import com.example.controlefinanceiro.viewmodel.ReceitaViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    receitaViewModel: ReceitaViewModel = hiltViewModel(),
    contaViewModel: ContaViewModel = hiltViewModel(),
    cartaoViewModel: CartaoViewModel = hiltViewModel()
) {
    var totalReceitas by remember { mutableDoubleStateOf(0.0) }
    var totalContas by remember { mutableDoubleStateOf(0.0) }
    var totalParcelas by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(Unit) {
        receitaViewModel.totalReceitas.collectLatest { total ->
            totalReceitas = total ?: 0.0
        }
        contaViewModel.totalContas.collectLatest { total ->
            totalContas = total ?: 0.0
        }
        cartaoViewModel.totalParcelas.collectLatest { total ->
            totalParcelas = total ?: 0.0
        }
    }

    val saldo = totalReceitas - (totalContas + totalParcelas)
    val totalDespesas = totalContas + totalParcelas

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Relatórios", color = YellowText, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackBackground,
                    titleContentColor = YellowText
                )
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackBackground)
                    .padding(padding)
            ) {
                item {
                    // Resumo Financeiro
                    Text(
                        text = "Resumo Financeiro",
                        color = YellowText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FinanceCard(
                            title = "Receitas",
                            value = totalReceitas,
                            color = GreenIncome,
                            modifier = Modifier.weight(1f)
                        )
                        FinanceCard(
                            title = "Despesas",
                            value = totalDespesas,
                            color = RedExpense,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                item {
                    // Saldo
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (saldo >= 0) GreenIncome.copy(alpha = 0.1f) else RedExpense.copy(alpha = 0.1f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Saldo Total",
                                color = YellowText,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "R$ ${"%.2f".format(saldo)}",
                                color = if (saldo >= 0) GreenIncome else RedExpense,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                // Análise de Receitas
                val receitas by receitaViewModel.receitas.collectAsState(initial = emptyList())
                if (receitas.isNotEmpty()) {
                    item {
                        Text(
                            text = "Análise de Receitas",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    
                    item {
                        val receitaMedia = receitas.map { it.valor }.average()
                        val receitaMaxima = receitas.maxOfOrNull { it.valor } ?: 0.0
                        val receitaMinima = receitas.minOfOrNull { it.valor } ?: 0.0
                        
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Média: R$ ${"%.2f".format(receitaMedia)}",
                                color = GreenIncome,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Maior: R$ ${"%.2f".format(receitaMaxima)}",
                                color = GreenIncome,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Menor: R$ ${"%.2f".format(receitaMinima)}",
                                color = GreenIncome,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                
                // Análise de Despesas
                val contas by contaViewModel.contas.collectAsState(initial = emptyList())
                if (contas.isNotEmpty()) {
                    item {
                        Text(
                            text = "Análise de Contas",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    
                    item {
                        val contaMedia = contas.map { it.valor }.average()
                        val contaMaxima = contas.maxOfOrNull { it.valor } ?: 0.0
                        val contaMinima = contas.minOfOrNull { it.valor } ?: 0.0
                        
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Média: R$ ${"%.2f".format(contaMedia)}",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Maior: R$ ${"%.2f".format(contaMaxima)}",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Menor: R$ ${"%.2f".format(contaMinima)}",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                
                // Análise de Cartões
                val cartoes by cartaoViewModel.cartoes.collectAsState(initial = emptyList())
                if (cartoes.isNotEmpty()) {
                    item {
                        Text(
                            text = "Análise de Parcelas",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    
                    item {
                        val parcelaMedia = cartoes.map { it.valorParcela }.average()
                        val totalParcelasPendentes = cartoes.sumOf { it.valorParcela }
                        val parcelasRestantes = cartoes.count()
                        
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Parcela Média: R$ ${"%.2f".format(parcelaMedia)}",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Total Pendente: R$ ${"%.2f".format(totalParcelasPendentes)}",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Parcelas Restantes: $parcelasRestantes",
                                color = RedExpense,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    )
}
