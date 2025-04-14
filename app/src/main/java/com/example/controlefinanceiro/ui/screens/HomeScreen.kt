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
import androidx.compose.ui.unit.sp // Adicionado para resolver o erro
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
    receitaViewModel: ReceitaViewModel,
    contaViewModel: ContaViewModel,
    cartaoViewModel: CartaoViewModel,
    onNavigateToReceita: () -> Unit,
    onNavigateToConta: () -> Unit,
    onNavigateToCartao: () -> Unit
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackBackground)
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Saldo
                Text(
                    text = "Saldo: R$ ${"%.2f".format(saldo)}",
                    color = if (saldo >= 0) GreenIncome else RedExpense,
                    fontSize = 24.sp, // Agora sp está resolvido
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onNavigateToReceita,
                        colors = ButtonDefaults.buttonColors(containerColor = GreenIncome)
                    ) {
                        Text("Adicionar Receita", color = BlackBackground)
                    }
                    Button(
                        onClick = onNavigateToConta,
                        colors = ButtonDefaults.buttonColors(containerColor = RedExpense)
                    ) {
                        Text("Adicionar Conta", color = BlackBackground)
                    }
                    Button(
                        onClick = onNavigateToCartao,
                        colors = ButtonDefaults.buttonColors(containerColor = RedExpense)
                    ) {
                        Text("Adicionar Cartão", color = BlackBackground)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Resumo
                Text("Receitas: R$ ${"%.2f".format(totalReceitas)}", color = GreenIncome)
                Text("Contas: R$ ${"%.2f".format(totalContas)}", color = RedExpense)
                Text("Parcelas: R$ ${"%.2f".format(totalParcelas)}", color = RedExpense)
                Spacer(modifier = Modifier.height(16.dp))

                // Listas
                Text("Receitas", color = YellowText, fontWeight = FontWeight.Bold)
                val receitas by receitaViewModel.receitas.collectAsState(initial = emptyList())
                LazyColumn {
                    items(receitas) { receita ->
                        Text(
                            "${receita.descricao}: R$ ${"%.2f".format(receita.valor)} (${receita.data})",
                            color = GreenIncome
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Contas", color = YellowText, fontWeight = FontWeight.Bold)
                val contas by contaViewModel.contas.collectAsState(initial = emptyList())
                LazyColumn {
                    items(contas) { conta ->
                        Text(
                            "${conta.descricao}: R$ ${"%.2f".format(conta.valor)} (${conta.dataVencimento})",
                            color = RedExpense
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Cartões", color = YellowText, fontWeight = FontWeight.Bold)
                val cartoes by cartaoViewModel.cartoes.collectAsState(initial = emptyList())
                LazyColumn {
                    items(cartoes) { cartao ->
                        Text(
                            "${cartao.descricao}: R$ ${"%.2f".format(cartao.valorParcela)} (${cartao.dataVencimento})",
                            color = RedExpense
                        )
                    }
                }
            }
        }
    )
}