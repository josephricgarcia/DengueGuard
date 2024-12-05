package com.example.mylogin.model

import com.google.firebase.auth.FirebaseAuth

class AdminLoginModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) {

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    val errorMessage = when (task.exception) {
                        is com.google.firebase.auth.FirebaseAuthInvalidUserException -> "No user found with this email."
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "Incorrect password."
                        else -> "Authentication failed. Please try again."
                    }
                    onFailure(errorMessage)
                }
            }
    }

    fun validateInputs(email: String, password: String): String? {
        return when {
            email.isEmpty() || password.isEmpty() -> "Please enter both email and password."
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Please enter a valid email address."
            else -> null
        }
    }
}
