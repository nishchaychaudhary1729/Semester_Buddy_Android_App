package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.theme.MyApplicationTheme

object AppRoutes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val NOTES = "notes"
    const val LECTURES = "lectures"
    const val NOTEBOOKS = "notebooks"
    const val ASSIGNMENTS = "assignments"
    const val AI_ASSISTANT = "ai_assistant"
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                SemesterApp()
            }
        }
    }
}
