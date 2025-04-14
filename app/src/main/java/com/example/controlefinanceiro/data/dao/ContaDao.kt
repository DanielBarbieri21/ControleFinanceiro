package com.example.controlefinanceiro.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.controlefinanceiro.data.entity.Conta
import kotlinx.coroutines.flow.Flow

@Dao
interface ContaDao {
    @Insert
    suspend fun insert(conta: Conta)

    @Query("SELECT * FROM contas")
    fun getAll(): Flow<List<Conta>>

    @Query("SELECT SUM(valor) FROM contas")
    fun getTotalContas(): Flow<Double?>
}