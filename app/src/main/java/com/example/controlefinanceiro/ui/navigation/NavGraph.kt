package com.example.controlefinanceiro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.controlefinanceiro.ui.screens.CartaoScreen
import com.example.controlefinanceiro.ui.screens.ContaScreen
import com.example.controlefinanceiro.ui.screens.HomeScreen
import com.example.controlefinanceiro.ui.screens.ReceitaScreen
import com.example.controlefinanceiro.viewmodel.CartaoViewModel
import com.example.controlefinanceiro.viewmodel.ContaViewModel
import com.example.controlefinanceiro.viewmodel.ReceitaViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    receitaViewModel: ReceitaViewModel,
    contaViewModel: ContaViewModel,
    cartaoViewModel: CartaoViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                receitaViewModel = receitaViewModel,
                contaViewModel = contaViewModel,
                cartaoViewModel = cartaoViewModel,
                onNavigateToReceita = { navController.navigate("receita") },
                onNavigateToConta = { navController.navigate("conta") },
                onNavigateToCartao = { navController.navigate("cartao") }
            )
        }
        composable("receita") {
            ReceitaScreen(
                viewModel = receitaViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("conta") {
            ContaScreen(
                viewModel = contaViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("cartao") {
            CartaoScreen(
                viewModel = cartaoViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}