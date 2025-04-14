package com.example.controlefinanceiro.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartoes")
data class Cartao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val descricao: String,
    val valorTotal: Double,
    val parcelas: Int,
    val parcelaAtual: Int,
    val valorParcela: Double,
    val dataVencimento: String // Formato: "dd/MM/yyyy"
)