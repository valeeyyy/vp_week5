package com.valerie.vp_week5.soal2.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valerie.vp_week5.soal2.model.QuizAnswer
import com.valerie.vp_week5.soal2.viewmodel.QuizViewModel

@Composable
internal fun QuizResultScreen(viewModel: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Finished!",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Your Score: ${viewModel.score} / ${viewModel.answers.size}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (viewModel.wrongAnswers.isEmpty()) {
            Text(
                text = "Perfect! No wrong answers.",
                style = MaterialTheme.typography.titleMedium,
                color = QuizPrimaryColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                items(viewModel.wrongAnswers) { wrongAnswer ->
                    WrongAnswerRow(answer = wrongAnswer, allAnswers = viewModel.answers)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = viewModel::restartQuiz,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = QuizPrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(QuizButtonHeight)
        ) {
            Text("Restart Quiz")
        }
    }
}

@Composable
private fun WrongAnswerRow(answer: QuizAnswer, allAnswers: List<QuizAnswer>) {
    val questionNumber = allAnswers.indexOf(answer) + 1

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = "Q$questionNumber: ${answer.question.text}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Your Answer: ${answer.selectedAnswer}",
            style = MaterialTheme.typography.bodySmall,
            color = QuizWrongColor
        )
        Text(
            text = "Correct Answer: ${answer.question.correctAnswer}",
            style = MaterialTheme.typography.bodySmall,
            color = QuizPrimaryColor
        )
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}
