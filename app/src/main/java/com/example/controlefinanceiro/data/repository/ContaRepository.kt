package com.example.controlefinanceiro.data.repository

import com.example.controlefinanceiro.data.dao.ContaDao
import com.example.controlefinanceiro.data.entity.Conta
import kotlinx.coroutines.flow.Flow

class ContaRepository(private val contaDao: ContaDao) {
    suspend fun insert(conta: Conta) {
        contaDao.insert(conta)
    }

    fun getAll(): Flow<List<Conta>> {
        return contaDao.getAll()
    }

    fun getTotalContas(): Flow<Double?> {
        return contaDao.getTotalContas()
    }
}