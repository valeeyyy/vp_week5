package com.valerie.vp_week5.soal2.data

import com.valerie.vp_week5.soal2.model.Question

class QuestionRepository {

    private val questionBank = listOf(
        Question(
            id = 1,
            text = "Ibu Kota Di Indonesia?",
            options = listOf("Surabaya", "Bandung", "Jakarta", "Medan"),
            correctAnswer = "Jakarta"
        ),
        Question(
            id = 2,
            text = "Apakah Mata Kuliah VP susah?",
            options = listOf("Sangat Mudah", "Mudah", "Sulit", "Sangat Sulit"),
            correctAnswer = "Sangat Mudah"
        ),
        Question(
            id = 3,
            text = "Natal dirayakan pada bulan?",
            options = listOf("Januari", "Juni", "Agustus", "Desember"),
            correctAnswer = "Desember"
        ),
        Question(
            id = 4,
            text = "Ada berapa hari dalam seminggu?",
            options = listOf("5", "6", "7", "11"),
            correctAnswer = "7"
        ),
        Question(
            id = 5,
            text = "Mata Kuliah VP pakai apa?",
            options = listOf("Figma", "Word", "Android Studio", "Excel"),
            correctAnswer = "Android Studio"
        )
    )

    val totalQuestionCount: Int get() = questionBank.size

    fun getRandomQuestions(count: Int): List<Question> {
        return questionBank.shuffled().take(count)
    }
}
