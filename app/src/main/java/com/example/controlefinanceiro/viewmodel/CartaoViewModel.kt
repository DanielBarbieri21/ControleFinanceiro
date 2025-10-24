package com.example.controlefinanceiro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlefinanceiro.data.entity.Cartao
import com.example.controlefinanceiro.data.repository.CartaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CartaoViewModel @Inject constructor(
    private val repository: CartaoRepository
) : ViewModel() {
    fun insert(descricao: String, valorTotal: Double, parcelas: Int, dataVencimento: String) {
        viewModelScope.launch {
            val valorParcela = valorTotal / parcelas
            for (parcelaAtual in 1..parcelas) {
                repository.insert(
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

    val cartoes: Flow<List<Cartao>> = repository.getAll()
    val totalParcelas: Flow<Double?> = repository.getTotalParcelas()
}