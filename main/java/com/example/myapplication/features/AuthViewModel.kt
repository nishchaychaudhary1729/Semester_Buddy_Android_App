//package com.example.myapplication.features
//
//import androidx.activity.ComponentActivity
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class AuthViewModel(
//    private val authManager: AuthManager,
////    private val authRepository: AuthRepository
//) : ViewModel() {
//
//    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
//    val signInState: StateFlow<SignInState> = _signInState
//
//    fun signInWithGoogle(activity: ComponentActivity) {
//        _signInState.value = SignInState.Loading
//        authManager.signInWithGoogle(
//            activity = activity,
//            onSuccess = { result ->
//                viewModelScope.launch {
//                    try {
//                        val authResponse = authRepository.verifyGoogleToken(result.idToken)
//                        _signInState.value = SignInState.Success(authResponse)
//                    } catch (e: Exception) {
//                        _signInState.value = SignInState.Error(e.message ?: "Verification failed")
//                    }
//                }
//            },
//            onError = { error ->
//                _signInState.value = SignInState.Error(error.message ?: "Sign-in failed")
//            }
//        )
//    }
//}
//
//sealed class SignInState {
//    data object Idle : SignInState()
//    data object Loading : SignInState()
//    data class Success(val authResponse: AuthResponse) : SignInState()
//    data class Error(val message: String) : SignInState()
//}