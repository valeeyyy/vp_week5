package com.valerie.vp_week5.soal2.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valerie.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
internal fun QuizQuestionScreen(viewModel: QuizViewModel) {
    val question = viewModel.currentQuestion ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Question ${viewModel.currentQuestionIndex + 1}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = question.text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        question.options.forEach { option ->
            QuizOptionButton(
                text = option,
                isSelected = viewModel.selectedOption == option,
                onClick = { viewModel.selectOption(option) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = viewModel::nextQuestion,
            enabled = viewModel.selectedOption != null,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizPrimaryColor,
                disabledContainerColor = QuizDisabledColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(QuizButtonHeight)
        ) {
            Text("Next")
        }
    }
}

@Composable
private fun QuizOptionButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) QuizPrimaryColor else QuizOptionBackground
    val textColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface

    Card(
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(QuizButtonHeight)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
