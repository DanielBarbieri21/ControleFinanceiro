package com.example.controlefinanceiro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.controlefinanceiro.ui.components.BalanceCard
import com.example.controlefinanceiro.ui.components.FinanceCard
import com.example.controlefinanceiro.ui.components.TransactionItem
import com.example.controlefinanceiro.ui.components.TransactionItemData
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
fun HomeScreen(
    onNavigateToReceita: () -> Unit,
    onNavigateToConta: () -> Unit,
    onNavigateToCartao: () -> Unit,
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Controle Financeiro", color = YellowText, fontWeight = FontWeight.Bold) },
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
                    // Saldo Principal
                    BalanceCard(balance = saldo)
                }
                
                item {
                    // Cards de Resumo
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
                            value = totalContas + totalParcelas,
                            color = RedExpense,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Lista de Receitas
                val receitas by receitaViewModel.receitas.collectAsState(initial = emptyList())
                if (receitas.isNotEmpty()) {
                    item {
                        Text(
                            text = "Receitas Recentes",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(receitas.take(5)) { receita ->
                        TransactionItem(
                            description = receita.descricao,
                            value = receita.valor,
                            date = receita.data,
                            color = GreenIncome
                        )
                    }
                }

                // Lista de Contas
                val contas by contaViewModel.contas.collectAsState(initial = emptyList())
                if (contas.isNotEmpty()) {
                    item {
                        Text(
                            text = "Contas Pendentes",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(contas.take(5)) { conta ->
                        TransactionItem(
                            description = conta.descricao,
                            value = conta.valor,
                            date = conta.dataVencimento,
                            color = RedExpense
                        )
                    }
                }

                // Lista de Cartões
                val cartoes by cartaoViewModel.cartoes.collectAsState(initial = emptyList())
                if (cartoes.isNotEmpty()) {
                    item {
                        Text(
                            text = "Parcelas do Cartão",
                            color = YellowText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(cartoes.take(5)) { cartao ->
                        TransactionItem(
                            description = cartao.descricao,
                            value = cartao.valorParcela,
                            date = cartao.dataVencimento,
                            color = RedExpense
                        )
                    }
                }
            }
        }
    )
}