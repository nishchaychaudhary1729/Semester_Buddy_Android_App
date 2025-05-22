package com.example.myapplication.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AssignmentsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Assignments & Solutions Tracker", style = MaterialTheme.typography.headlineSmall)
        Text("Track deadlines, manage submissions, and store solutions.")
        // TODO: Implement UI for assignments
        // - List of assignments with due dates, status (to-do, in-progress, submitted, graded)
        // - Option to attach files (assignment brief, your solution)
        // - Calendar view for deadlines
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentsScreenPreview() {
    MyApplicationTheme {
        AssignmentsScreen()
    }
}