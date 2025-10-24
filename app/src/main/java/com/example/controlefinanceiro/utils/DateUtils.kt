package com.example.controlefinanceiro.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    
    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }
    
    fun parseDate(dateString: String): Date? {
        return try {
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
    
    fun getCurrentDate(): String {
        return formatDate(Date())
    }
    
    fun isValidDate(dateString: String): Boolean {
        return parseDate(dateString) != null
    }
    
    fun addMonths(date: Date, months: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, months)
        return calendar.time
    }
}
