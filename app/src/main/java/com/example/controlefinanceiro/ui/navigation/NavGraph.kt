package com.example.controlefinanceiro.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.controlefinanceiro.ui.components.BottomNavigationBar
import com.example.controlefinanceiro.ui.screens.CartaoScreen
import com.example.controlefinanceiro.ui.screens.ContaScreen
import com.example.controlefinanceiro.ui.screens.HomeScreen
import com.example.controlefinanceiro.ui.screens.ReceitaScreen
import com.example.controlefinanceiro.ui.screens.ReportsScreen
import com.example.controlefinanceiro.ui.theme.BlackBackground

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        },
        containerColor = BlackBackground
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(
                    onNavigateToReceita = { navController.navigate("receita") },
                    onNavigateToConta = { navController.navigate("conta") },
                    onNavigateToCartao = { navController.navigate("cartao") }
                )
            }
            composable("receita") {
                ReceitaScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable("conta") {
                ContaScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable("cartao") {
                CartaoScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable("reports") {
                ReportsScreen()
            }
        }
    }
}