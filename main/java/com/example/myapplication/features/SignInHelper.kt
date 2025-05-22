//package com.example.myapplication.features
//
//import android.widget.Button
//import androidx.activity.ComponentActivity
//import androidx.lifecycle.LifecycleOwner
//
//// features/auth/SignInHelper.kt
//class SignInHelper(
//    private val activity: ComponentActivity,
//    private val viewModel: AuthViewModel
//) {
//    fun setupSignInButton(button: Button) {
//        button.setOnClickListener {
//            viewModel.signInWithGoogle(activity)
//        }
//    }
//
//    fun observeSignInState(lifecycleOwner: LifecycleOwner, onStateChange: (SignInState) -> Unit) {
//        viewModel.signInState.observe(lifecycleOwner) { state ->
//            onStateChange(state)
//        }
//    }
//}