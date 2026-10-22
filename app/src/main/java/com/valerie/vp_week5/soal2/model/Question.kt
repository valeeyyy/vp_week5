package com.valerie.vp_week5.soal2.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)
