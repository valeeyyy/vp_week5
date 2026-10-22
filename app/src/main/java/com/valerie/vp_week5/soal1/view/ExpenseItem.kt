package com.valerie.vp_week5.soal1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.valerie.vp_week5.soal1.model.Expense

private val ExpenseNameColor = Color(0xFF4B4F6B)

@Composable
internal fun ExpenseItem(
    expense: Expense,
    isEditing: Boolean,
    editAmountInput: String,
    editErrorMessage: String?,
    onEditAmountChange: (String) -> Unit,
    onStartEdit: () -> Unit,
    onConfirmEdit: () -> Unit,
    onCancelEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = ExpenseItemBackground),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Name : ${expense.name}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = ExpenseNameColor
                )
                if (isEditing) {
                    EditAmountField(
                        value = editAmountInput,
                        errorMessage = editErrorMessage,
                        onValueChange = onEditAmountChange
                    )
                } else {
                    Text(
                        text = formatRupiah(expense.amount),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (isEditing) {
                IconButton(onClick = onConfirmEdit) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save Amount",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onCancelEdit) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel Edit"
                    )
                }
            } else {
                IconButton(onClick = onStartEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Amount",
                        tint = EditColor
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Expense",
                        tint = DeleteColor
                    )
                }
            }
        }
    }
}
