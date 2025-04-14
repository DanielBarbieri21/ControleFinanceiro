package com.example.controlefinanceiro.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.controlefinanceiro.data.entity.Receita
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitaDao {
    @Insert
    suspend fun insert(receita: Receita)

    @Query("SELECT * FROM receitas")
    fun getAll(): Flow<List<Receita>>

    @Query("SELECT SUM(valor) FROM receitas")
    fun getTotalReceitas(): Flow<Double?>
}