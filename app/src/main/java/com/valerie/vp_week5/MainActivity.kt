package com.valerie.vp_week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valerie.vp_week5.ui.theme.Vp_week5Theme

// Uncomment import di bawah sesuai soal yang ingin dijalankan

//import com.valerie.vp_week5.soal1.view.ExpenseScreen //soal1
//import com.valerie.vp_week5.soal1.viewmodel.ExpenseViewModel //soal1
import com.valerie.vp_week5.soal2.view.QuizScreen //soal2
import com.valerie.vp_week5.soal2.viewmodel.QuizViewModel //soal2

class MainActivity : ComponentActivity() {
    // Uncomment ViewModel di bawah sesuai soal yang ingin dijalankan

    //private val expenseViewModel: ExpenseViewModel by viewModels() //soal1
    private val quizViewModel: QuizViewModel by viewModels() //soal2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vp_week5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Uncomment screen di bawah sesuai soal yang ingin dijalankan

                    //ExpenseScreen(viewModel = expenseViewModel, modifier = Modifier.padding(innerPadding)) //soal1
                    QuizScreen(viewModel = quizViewModel, modifier = Modifier.padding(innerPadding)) //soal2
                }
            }
        }
    }
}
