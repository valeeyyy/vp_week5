package com.valerie.vp_week5.soal1.data

import androidx.compose.runtime.mutableStateListOf
import com.valerie.vp_week5.soal1.model.Expense

class ExpenseRepository {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    fun addExpense(expense: Expense) {
        _expenses.add(0, expense)
    }

    fun deleteExpense(id: String) {
        _expenses.removeAll { it.id == id }
    }

    fun updateExpenseAmount(id: String, amount: Long) {
        val index = _expenses.indexOfFirst { it.id == id }
        if (index != -1) {
            _expenses[index] = _expenses[index].copy(amount = amount)
        }
    }
}
