package com.valerie.vp_week5.soal2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valerie.vp_week5.soal2.viewmodel.QuizScreenState
import com.valerie.vp_week5.soal2.viewmodel.QuizViewModel

internal val QuizScreenBackground = Color(0xFFF5F3FA)
internal val QuizPrimaryColor = Color(0xFF3B4371)
internal val QuizPrimaryLightColor = Color(0xFF6C77A6)
internal val QuizOptionBackground = Color(0xFFE4E1F7)
internal val QuizDisabledColor = Color(0xFFD9D9DB)
internal val QuizWrongColor = Color(0xFFD64545)

internal val QuizButtonHeight = 45.dp

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel(), modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(QuizScreenBackground)
    ) {
        when (viewModel.screenState) {
            QuizScreenState.START -> QuizStartScreen(viewModel = viewModel)
            QuizScreenState.QUESTION -> QuizQuestionScreen(viewModel = viewModel)
            QuizScreenState.RESULT -> QuizResultScreen(viewModel = viewModel)
        }
    }
}
