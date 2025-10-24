package com.example.controlefinanceiro.utils

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    
    fun formatCurrency(value: Double): String {
        return currencyFormat.format(value)
    }
    
    fun parseCurrency(currencyString: String): Double? {
        return try {
            // Remove símbolos de moeda e espaços
            val cleanString = currencyString
                .replace("R$", "")
                .replace(" ", "")
                .replace(".", "")
                .replace(",", ".")
            cleanString.toDouble()
        } catch (e: Exception) {
            null
        }
    }
    
    fun isValidCurrency(currencyString: String): Boolean {
        return parseCurrency(currencyString) != null
    }
}
