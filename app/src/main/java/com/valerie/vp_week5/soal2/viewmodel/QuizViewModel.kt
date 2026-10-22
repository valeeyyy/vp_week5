package com.valerie.vp_week5.soal2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.valerie.vp_week5.soal2.data.QuestionRepository
import com.valerie.vp_week5.soal2.model.Question
import com.valerie.vp_week5.soal2.model.QuizAnswer

enum class QuizScreenState { START, QUESTION, RESULT }

class QuizViewModel(
    private val repository: QuestionRepository = QuestionRepository()
) : ViewModel() {

    val maxQuestionCount: Int get() = repository.totalQuestionCount

    var screenState by mutableStateOf(QuizScreenState.START)
        private set

    var numberOfQuestionsInput by mutableStateOf("")
        private set
    var numberError by mutableStateOf<String?>(null)
        private set

    val canStartQuiz: Boolean
        get() = numberOfQuestionsInput.toIntOrNull()?.let { it in 1..maxQuestionCount } == true

    var questions by mutableStateOf<List<Question>>(emptyList())
        private set
    var currentQuestionIndex by mutableStateOf(0)
        private set
    var selectedOption by mutableStateOf<String?>(null)
        private set

    private val _answers = mutableStateListOf<QuizAnswer>()
    val answers: List<QuizAnswer> get() = _answers

    val currentQuestion: Question? get() = questions.getOrNull(currentQuestionIndex)
    val isLastQuestion: Boolean get() = currentQuestionIndex == questions.lastIndex
    val score: Int get() = _answers.count { it.selectedAnswer == it.question.correctAnswer }
    val wrongAnswers: List<QuizAnswer> get() = _answers.filter { it.selectedAnswer != it.question.correctAnswer }

    fun onNumberOfQuestionsChange(value: String) {
        val filtered = value.filter { it.isDigit() }
        numberOfQuestionsInput = filtered

        val count = filtered.toIntOrNull()
        numberError = when {
            filtered.isEmpty() -> null
            count != null && count > maxQuestionCount -> "Maximum is $maxQuestionCount questions"
            count == null || count <= 0 -> "Number of questions is required"
            else -> null
        }
    }

    fun startQuiz() {
        val count = numberOfQuestionsInput.toIntOrNull() ?: 0
        if (count <= 0) {
            numberError = "Number of questions is required"
            return
        }
        if (count > maxQuestionCount) {
            numberError = "Maximum is $maxQuestionCount questions"
            return
        }

        questions = repository.getRandomQuestions(count)
        currentQuestionIndex = 0
        selectedOption = null
        _answers.clear()
        screenState = QuizScreenState.QUESTION
    }

    fun selectOption(option: String) {
        selectedOption = option
    }

    fun nextQuestion() {
        val question = currentQuestion ?: return
        val answer = selectedOption ?: return
        _answers.add(QuizAnswer(question = question, selectedAnswer = answer))

        if (isLastQuestion) {
            screenState = QuizScreenState.RESULT
        } else {
            currentQuestionIndex += 1
            selectedOption = null
        }
    }

    fun restartQuiz() {
        screenState = QuizScreenState.START
        questions = emptyList()
        currentQuestionIndex = 0
        selectedOption = null
        _answers.clear()
        numberOfQuestionsInput = ""
        numberError = null
    }
}
