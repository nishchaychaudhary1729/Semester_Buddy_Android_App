package com.example.myapplication.features

import android.app.Activity
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.CustomCredential
import com.example.myapplication.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthManager(
    private val context: Context,
    private val authGoogleId: AuthGoogleId = AuthGoogleId(context.getString(R.string.google_web_client_id)),
    private val credentialManager: CredentialManager = CredentialManager.create(context)
) {
    fun signInWithGoogle(
        activity: Any?,
        onSuccess: (GoogleSignInResult) -> Unit,
        onError: (String) -> Unit
    ) {
        // 1. Verify Play Services
        if (!isGooglePlayServicesAvailable(activity as Activity)) {
            onError("Update Google Play Services")
            return
        }

        // 2. Build request using AuthGoogleId
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(authGoogleId.buildGoogleIdOption())
            .build()

        // 3. Execute sign-in
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = activity
                )
                handleSignIn(result, onSuccess, onError)
            } catch (e: GetCredentialException) {
                handleFailure(e, onError)
            }
        }
    }

    private fun isGooglePlayServicesAvailable(activity: Activity): Boolean {
        val code = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(activity)
        if (code != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance()
                .getErrorDialog(activity, code, 9001)?.show()
            return false
        }
        return true
    }

    private fun handleSignIn(
        result: GetCredentialResponse,
        onSuccess: (GoogleSignInResult) -> Unit,
        onError: (String) -> Unit
    ) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        onSuccess(
                            GoogleSignInResult(
                                idToken = googleCredential.idToken,
                                displayName = googleCredential.displayName,
                                profilePictureUri = googleCredential.profilePictureUri.toString()
                            )
                        )
                    } catch (e: GoogleIdTokenParsingException) {
                        onError(e.toString())
                    }
                }
            }
            else -> onError(Exception("Unexpected credential type").toString())
        }
    }

    private fun handleFailure(e: GetCredentialException, onError: (String) -> Unit) {
        when (e) {
            is NoCredentialException -> onError(Exception("No credentials found").toString())
            is GetCredentialCancellationException -> {} // User canceled, no action needed
            else -> onError(e.toString())
        }
    }

//    companion object {
//        private const val TAG = "AuthManager"
//    }
}

data class GoogleSignInResult(
    val idToken: String,
    val displayName: String?,
    val profilePictureUri: String?
)