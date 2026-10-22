package com.valerie.vp_week5.soal2.view

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valerie.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
internal fun QuizStartScreen(viewModel: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mini Quiz",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "How many questions do you want? (1 - ${viewModel.maxQuestionCount})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val isError = viewModel.numberError != null
        val borderColor = when {
            isError -> QuizWrongColor
            isFocused -> QuizPrimaryColor
            else -> MaterialTheme.colorScheme.outline
        }

        OutlinedTextField(
            value = viewModel.numberOfQuestionsInput,
            onValueChange = viewModel::onNumberOfQuestionsChange,
            label = { Text("Number of questions") },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            interactionSource = interactionSource,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                focusedLabelColor = QuizPrimaryColor,
                errorLabelColor = QuizWrongColor,
                cursorColor = QuizPrimaryColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = borderColor, shape = OutlinedTextFieldDefaults.shape)
        )

        if (viewModel.numberError != null) {
            Text(
                text = viewModel.numberError.orEmpty(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = viewModel::startQuiz,
            enabled = viewModel.canStartQuiz,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizPrimaryLightColor,
                disabledContainerColor = QuizDisabledColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(QuizButtonHeight)
        ) {
            Text(
                text = "Start Quiz",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
