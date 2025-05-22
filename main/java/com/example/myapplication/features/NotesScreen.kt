package com.example.myapplication.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun NotesScreen(modifier: Modifier = Modifier) {
    // Note: TopAppBar is now handled globally in SemesterApp for non-dashboard screens
    // If you need a specific TopAppBar here, you can add another Scaffold
    // or customize the global one based on route.
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Notes Management Screen", style = MaterialTheme.typography.headlineSmall)
        Text("Here you will manage your notes for different subjects.")
        // TODO: Implement UI for creating, viewing, editing notes
        // - List of notes/subjects
        // - Note editor
        // - Search functionality
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    MyApplicationTheme {
        NotesScreen()
    }
}