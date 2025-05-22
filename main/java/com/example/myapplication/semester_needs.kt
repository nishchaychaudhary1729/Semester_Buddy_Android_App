package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.features.AiAssistantScreen
import com.example.myapplication.features.AssignmentsScreen
import com.example.myapplication.features.AuthManager
//import com.example.myapplication.features.AuthViewModel
import com.example.myapplication.features.LecturesScreen
import com.example.myapplication.features.NotebooksScreen
import com.example.myapplication.features.NotesScreen
//import com.example.myapplication.features.SignInState
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SemesterApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentScreenRoute != AppRoutes.DASHBOARD) {
                TopAppBar(
                    title = {
                        Text(
                            when (currentScreenRoute) {
                                AppRoutes.NOTES -> "Notes Management"
                                AppRoutes.LECTURES -> "Lecture Organizer"
                                AppRoutes.NOTEBOOKS -> "Digital Notebooks"
                                AppRoutes.ASSIGNMENTS -> "Assignments & Solutions"
                                AppRoutes.AI_ASSISTANT -> "AI Study Assistant"
                                else -> "My Semester Companion"
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.LOGIN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.LOGIN) {
                LoginScreen<Any>(navController = navController)
            }
            composable(AppRoutes.DASHBOARD) {
                SemesterDashboardScreen(navController = navController)
            }
            composable(AppRoutes.NOTES) {
                NotesScreen()
            }
            composable(AppRoutes.LECTURES) {
                LecturesScreen()
            }
            composable(AppRoutes.NOTEBOOKS) {
                NotebooksScreen()
            }
            composable(AppRoutes.ASSIGNMENTS) {
                AssignmentsScreen()
            }
            composable(AppRoutes.AI_ASSISTANT) {
                AiAssistantScreen()
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <ComponentActivity> LoginScreen(
    navController: NavHostController,
    onLoginSuccess: () -> Unit = { navController.navigate(AppRoutes.DASHBOARD) }
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email/Password Fields
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email Login Button
        Button(
            onClick = {
                isLoading = true
                // Simple validation
                if (email.isNotBlank() && password.isNotBlank()) {
                    // In a real app, you would authenticate with your backend here
                    // For now, just simulate success
                    onLoginSuccess()
                } else {
                    Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                }
                isLoading = false
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Login with Email")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google Sign-In Button
        Button(
            onClick = {
                isLoading = true
                val activity = context as? ComponentActivity
                activity?.let {
                    authManager.signInWithGoogle(
                        activity = it,
                        onSuccess = { result ->
                            isLoading = false
                            // Show success message
                            Toast.makeText(
                                context,
                                "Welcome, ${result.displayName ?: "User"}!",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Navigate to dashboard
                            navController.navigate(AppRoutes.DASHBOARD) {
                                // Clear back stack so user can't go back to login
                                popUpTo(AppRoutes.LOGIN) { inclusive = true }
                            }
                        },
                        onError = { error ->
                            isLoading = false
                            // Show actual error message from the authManager
                            Toast.makeText(
                                context,
                                "Login failed: $error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                } ?: run {
                    isLoading = false
                    Toast.makeText(context, "Could not get activity", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text("Continue with Google")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /* Handle forgot password */ }
        ) {
            Text("Forgot Password?")
        }

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Don't have an account?",
            modifier = Modifier.padding(top = 8.dp)
        )
        TextButton(
            onClick = { /* Navigate to sign up */ }
        ) {
            Text("Sign Up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SemesterDashboardScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Semester Companion") },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Changed for button list
        ) {
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Navigate to a section:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FeatureButton(text = "Notes Management") { navController.navigate(AppRoutes.NOTES) }
            FeatureButton(text = "Lecture Organizer") { navController.navigate(AppRoutes.LECTURES) }
            FeatureButton(text = "Digital Notebooks") { navController.navigate(AppRoutes.NOTEBOOKS) }
            FeatureButton(text = "Assignments & Solutions") { navController.navigate(AppRoutes.ASSIGNMENTS) }
            FeatureButton(text = "AI Study Assistant") { navController.navigate(AppRoutes.AI_ASSISTANT) }
        }
    }
}

@Composable
fun FeatureButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text)
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun SemesterAppPreview() {
    MyApplicationTheme {
        SemesterApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SemesterDashboardScreenPreview() {
    MyApplicationTheme {
        // For preview, pass a dummy NavController or wrap in a way that doesn't crash
        SemesterDashboardScreen(navController = rememberNavController())
    }
}

