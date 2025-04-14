package com.example.controlefinanceiro.data.repository

import com.example.controlefinanceiro.data.dao.ReceitaDao
import com.example.controlefinanceiro.data.entity.Receita
import kotlinx.coroutines.flow.Flow

class ReceitaRepository(private val receitaDao: ReceitaDao) {
    suspend fun insert(receita: Receita) {
        receitaDao.insert(receita)
    }

    fun getAll(): Flow<List<Receita>> {
        return receitaDao.getAll()
    }

    fun getTotalReceitas(): Flow<Double?> {
        return receitaDao.getTotalReceitas()
    }
}