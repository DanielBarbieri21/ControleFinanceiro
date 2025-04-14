package com.example.controlefinanceiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.controlefinanceiro.data.database.AppDatabase
import com.example.controlefinanceiro.data.repository.CartaoRepository
import com.example.controlefinanceiro.data.repository.ContaRepository
import com.example.controlefinanceiro.data.repository.ReceitaRepository
import com.example.controlefinanceiro.ui.navigation.SetupNavGraph
import com.example.controlefinanceiro.ui.theme.ControleFinanceiroTheme
import com.example.controlefinanceiro.viewmodel.CartaoViewModel
import com.example.controlefinanceiro.viewmodel.ContaViewModel
import com.example.controlefinanceiro.viewmodel.ReceitaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o banco de dados
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "controle_financeiro_db"
        ).build()

        // Inicializar repositórios
        val receitaRepository = ReceitaRepository(db.receitaDao())
        val contaRepository = ContaRepository(db.contaDao())
        val cartaoRepository = CartaoRepository(db.cartaoDao())

        // Inicializar ViewModels
        val receitaViewModel = ReceitaViewModel(receitaRepository)
        val contaViewModel = ContaViewModel(contaRepository)
        val cartaoViewModel = CartaoViewModel(cartaoRepository)

        setContent {
            ControleFinanceiroTheme {
                App(
                    receitaViewModel = receitaViewModel,
                    contaViewModel = contaViewModel,
                    cartaoViewModel = cartaoViewModel
                )
            }
        }
    }
}

@Composable
fun App(
    receitaViewModel: ReceitaViewModel,
    contaViewModel: ContaViewModel,
    cartaoViewModel: CartaoViewModel
) {
    val navController = rememberNavController()
    SetupNavGraph(
        navController = navController,
        receitaViewModel = receitaViewModel,
        contaViewModel = contaViewModel,
        cartaoViewModel = cartaoViewModel
    )
}