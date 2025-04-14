package com.example.controlefinanceiro.data.repository

import com.example.controlefinanceiro.data.dao.CartaoDao
import com.example.controlefinanceiro.data.entity.Cartao
import kotlinx.coroutines.flow.Flow

class CartaoRepository(private val cartaoDao: CartaoDao) {
    suspend fun insert(cartao: Cartao) {
        cartaoDao.insert(cartao)
    }

    fun getAll(): Flow<List<Cartao>> {
        return cartaoDao.getAll()
    }

    fun getTotalParcelas(): Flow<Double?> {
        return cartaoDao.getTotalParcelas()
    }
}