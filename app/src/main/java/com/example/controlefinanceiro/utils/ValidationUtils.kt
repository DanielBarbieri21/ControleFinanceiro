package com.example.controlefinanceiro.utils

object ValidationUtils {
    
    fun isValidDescription(description: String): Boolean {
        return description.isNotBlank() && description.length >= 3
    }
    
    fun isValidAmount(amount: String): Boolean {
        return amount.isNotBlank() && amount.toDoubleOrNull() != null && amount.toDoubleOrNull()!! > 0
    }
    
    fun isValidDate(dateString: String): Boolean {
        return DateUtils.isValidDate(dateString)
    }
    
    fun isValidInstallments(installments: String): Boolean {
        val installmentsInt = installments.toIntOrNull()
        return installments.isNotBlank() && installmentsInt != null && installmentsInt > 0 && installmentsInt <= 24
    }
    
    fun getDescriptionError(description: String): String? {
        return when {
            description.isBlank() -> "Descrição é obrigatória"
            description.length < 3 -> "Descrição deve ter pelo menos 3 caracteres"
            else -> null
        }
    }
    
    fun getAmountError(amount: String): String? {
        return when {
            amount.isBlank() -> "Valor é obrigatório"
            amount.toDoubleOrNull() == null -> "Valor deve ser um número válido"
            amount.toDoubleOrNull()!! <= 0 -> "Valor deve ser maior que zero"
            else -> null
        }
    }
    
    fun getDateError(dateString: String): String? {
        return when {
            dateString.isBlank() -> "Data é obrigatória"
            !DateUtils.isValidDate(dateString) -> "Data deve estar no formato dd/MM/yyyy"
            else -> null
        }
    }
    
    fun getInstallmentsError(installments: String): String? {
        return when {
            installments.isBlank() -> "Número de parcelas é obrigatório"
            installments.toIntOrNull() == null -> "Número de parcelas deve ser um número válido"
            installments.toIntOrNull()!! <= 0 -> "Número de parcelas deve ser maior que zero"
            installments.toIntOrNull()!! > 24 -> "Número de parcelas não pode ser maior que 24"
            else -> null
        }
    }
}
