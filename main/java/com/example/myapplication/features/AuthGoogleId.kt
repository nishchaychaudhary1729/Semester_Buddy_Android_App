package com.example.myapplication.features

//import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

class AuthGoogleId(private val webClientId: String) {

    fun buildGoogleIdOption(
        filterByAuthorizedAccounts: Boolean = false,
        autoSelectEnabled: Boolean = false,
        nonce: String? = null
    ): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
            .setServerClientId(webClientId)
            .setAutoSelectEnabled(autoSelectEnabled)
            .setNonce(nonce)
            .build()
    }

//    fun createSignInRequest(googleIdOption: GetGoogleIdOption): GetCredentialRequest {
//        return GetCredentialRequest.Builder()
//            .addCredentialOption(googleIdOption)
//            .build()
//    }
}