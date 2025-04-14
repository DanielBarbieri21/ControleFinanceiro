package com.example.controlefinanceiro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlefinanceiro.data.entity.Conta
import com.example.controlefinanceiro.data.repository.ContaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class ContaViewModel(private val repository: ContaRepository) : ViewModel() {
    fun insert(descricao: String, valor: Double, dataVencimento: String) {
        viewModelScope.launch {
            repository.insert(Conta(descricao = descricao, valor = valor, dataVencimento = dataVencimento))
        }
    }

    val contas: Flow<List<Conta>> = repository.getAll()
    val totalContas: Flow<Double?> = repository.getTotalContas()
}