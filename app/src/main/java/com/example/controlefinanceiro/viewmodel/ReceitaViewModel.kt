package com.example.controlefinanceiro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlefinanceiro.data.entity.Receita
import com.example.controlefinanceiro.data.repository.ReceitaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class ReceitaViewModel(private val repository: ReceitaRepository) : ViewModel() {
    fun insert(descricao: String, valor: Double, data: String) {
        viewModelScope.launch {
            repository.insert(Receita(descricao = descricao, valor = valor, data = data))
        }
    }

    val receitas: Flow<List<Receita>> = repository.getAll()
    val totalReceitas: Flow<Double?> = repository.getTotalReceitas()
}