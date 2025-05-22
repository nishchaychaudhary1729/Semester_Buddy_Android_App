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
fun LecturesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Lecture Organizer Screen", style = MaterialTheme.typography.headlineSmall)
        Text("Manage lecture recordings, links, and transcripts here.")
        // TODO: Implement UI for lectures
        // - List of lectures by course
        // - Option to add recording (link or file)
        // - Display/link transcripts
        // - AI features: Summarize transcript, Q&A on lecture content
    }
}

@Preview(showBackground = true)
@Composable
fun LecturesScreenPreview() {
    MyApplicationTheme {
        LecturesScreen()
    }
}