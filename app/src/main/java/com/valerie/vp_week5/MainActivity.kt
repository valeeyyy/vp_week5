package com.valerie.vp_week5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.valerie.vp_week5.soal1.view.ExpenseScreen
import com.valerie.vp_week5.ui.theme.Vp_week5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vp_week5Theme {
                ExpenseScreen()
            }
        }
    }
}
