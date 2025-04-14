package com.example.controlefinanceiro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlefinanceiro.data.entity.Cartao
import com.example.controlefinanceiro.data.repository.CartaoRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class CartaoViewModel(private val repository: CartaoRepository) : ViewModel() {
    fun insert(descricao: String, valorTotal: Double, parcelas: Int, dataVencimento: String) {
        viewModelScope.launch {
            val valorParcela = valorTotal / parcelas
            for (parcelaAtual in 1..parcelas) {
                repository.insert( // Este método agora deve ser encontrado
                    Cartao(
                        descricao = "$descricao (Parcela $parcelaAtual/$parcelas)",
                        valorTotal = valorTotal,
                        parcelas = parcelas,
                        parcelaAtual = parcelaAtual,
                        valorParcela = valorParcela,
                        dataVencimento = dataVencimento
                    )
                )
            }
        }
    }

    val cartoes: Flow<List<Cartao>> = repository.getAll() // Este método agora deve ser encontrado
    val totalParcelas: Flow<Double?> = repository.getTotalParcelas() // Este método agora deve ser encontrado
}