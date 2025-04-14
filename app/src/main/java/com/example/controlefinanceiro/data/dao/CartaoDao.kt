package com.example.controlefinanceiro.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.controlefinanceiro.data.entity.Cartao
import kotlinx.coroutines.flow.Flow

@Dao
interface CartaoDao {
    @Insert
    suspend fun insert(cartao: Cartao)

    @Query("SELECT * FROM cartoes")
    fun getAll(): Flow<List<Cartao>>

    @Query("SELECT SUM(valorParcela) FROM cartoes")
    fun getTotalParcelas(): Flow<Double?>
}