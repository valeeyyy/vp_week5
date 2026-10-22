package com.valerie.vp_week5.soal1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.valerie.vp_week5.soal1.data.ExpenseRepository
import com.valerie.vp_week5.soal1.model.Expense

class ExpenseViewModel(
    private val repository: ExpenseRepository = ExpenseRepository()
) : ViewModel() {

    val expenses: List<Expense> get() = repository.expenses

    val totalExpense: Long
        get() = repository.expenses.sumOf { it.amount }

    var nameInput by mutableStateOf("")
        private set
    var amountInput by mutableStateOf("")
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var editingExpenseId by mutableStateOf<String?>(null)
        private set
    var editAmountInput by mutableStateOf("")
        private set
    var editErrorMessage by mutableStateOf<String?>(null)
        private set

    fun onNameChange(value: String) {
        nameInput = value
        errorMessage = null
    }

    fun onAmountChange(value: String) {
        amountInput = value.filter { it.isDigit() }
        errorMessage = null
    }

    fun addExpense() {
        if (nameInput.isBlank()) {
            errorMessage = "Expense name can't be empty"
            return
        }
        val amount = amountInput.toLongOrNull() ?: 0L
        if (amount <= 0) {
            errorMessage = "Amount must be greater than 0"
            return
        }

        repository.addExpense(Expense(name = nameInput.trim(), amount = amount))
        nameInput = ""
        amountInput = ""
        errorMessage = null
    }

    fun deleteExpense(id: String) {
        repository.deleteExpense(id)
        if (editingExpenseId == id) cancelEdit()
    }

    fun startEdit(expense: Expense) {
        editingExpenseId = expense.id
        editAmountInput = expense.amount.toString()
        editErrorMessage = null
    }

    fun onEditAmountChange(value: String) {
        editAmountInput = value.filter { it.isDigit() }
        editErrorMessage = null
    }

    fun confirmEdit() {
        val id = editingExpenseId ?: return
        val newAmount = editAmountInput.toLongOrNull() ?: 0L
        if (newAmount <= 0) {
            editErrorMessage = "Amount must be greater than 0"
            return
        }

        repository.updateExpenseAmount(id, newAmount)
        cancelEdit()
    }

    fun cancelEdit() {
        editingExpenseId = null
        editAmountInput = ""
        editErrorMessage = null
    }
}
