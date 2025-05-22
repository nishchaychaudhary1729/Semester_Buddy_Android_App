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
fun NotebooksScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Digital Notebooks Screen", style = MaterialTheme.typography.headlineSmall)
        Text("Your space for scratchpads, brainstorming, and quick jots.")
        // TODO: Implement digital notebook functionality
        // - Could be simple text areas per notebook
        // - Or more advanced: drawing canvas, rich text editing
    }
}

@Preview(showBackground = true)
@Composable
fun NotebooksScreenPreview() {
    MyApplicationTheme {
        NotebooksScreen()
    }
}