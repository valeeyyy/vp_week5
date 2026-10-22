package com.valerie.vp_week5.soal1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valerie.vp_week5.soal1.viewmodel.ExpenseViewModel
import java.text.NumberFormat
import java.util.Locale

internal val ScreenBackground = Color(0xFFF5F3FA)
internal val ExpenseItemBackground = Color(0xFFD9DDF7)
internal val DeleteColor = Color(0xFFD64545)
internal val EditColor = Color(0xFFD7A32E)
internal val AddButtonBackground = Color(0xFFEBEBED)
internal val AddButtonIcon = Color(0xFF9E9E9E)

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = viewModel(), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Expenses",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Total Expense : ${formatRupiah(viewModel.totalExpense)}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.nameInput,
            onValueChange = viewModel::onNameChange,
            label = { Text("Expense Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = viewModel.amountInput,
                onValueChange = viewModel::onAmountChange,
                label = { Text("Amount of Expense") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = viewModel.errorMessage != null,
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = viewModel::addExpense,
                modifier = Modifier
                    .size(48.dp)
                    .background(AddButtonBackground, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Expense",
                    tint = AddButtonIcon
                )
            }
        }

        if (viewModel.errorMessage != null) {
            Text(
                text = viewModel.errorMessage.orEmpty(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(viewModel.expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    isEditing = viewModel.editingExpenseId == expense.id,
                    editAmountInput = viewModel.editAmountInput,
                    editErrorMessage = viewModel.editErrorMessage,
                    onEditAmountChange = viewModel::onEditAmountChange,
                    onStartEdit = { viewModel.startEdit(expense) },
                    onConfirmEdit = viewModel::confirmEdit,
                    onCancelEdit = viewModel::cancelEdit,
                    onDelete = { viewModel.deleteExpense(expense.id) }
                )
            }
        }
    }
}

internal fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getNumberInstance(Locale.Builder().setLanguage("id").setRegion("ID").build())
    return "Rp ${formatter.format(amount)}"
}
