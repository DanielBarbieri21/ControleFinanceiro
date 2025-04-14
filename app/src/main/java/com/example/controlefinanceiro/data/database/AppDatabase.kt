package com.example.controlefinanceiro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.controlefinanceiro.data.dao.ContaDao
import com.example.controlefinanceiro.data.dao.ReceitaDao
import com.example.controlefinanceiro.data.dao.CartaoDao
import com.example.controlefinanceiro.data.entity.Conta
import com.example.controlefinanceiro.data.entity.Receita
import com.example.controlefinanceiro.data.entity.Cartao

@Database(entities = [Receita::class, Conta::class, Cartao::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receitaDao(): ReceitaDao
    abstract fun contaDao(): ContaDao
    abstract fun cartaoDao(): CartaoDao
}