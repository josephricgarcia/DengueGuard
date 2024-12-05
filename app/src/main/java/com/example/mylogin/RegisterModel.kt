package com.example.mylogin.model

import com.google.firebase.auth.FirebaseAuth

class RegisterModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) {
    fun registerUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    val errorMessage = task.exception?.message ?: "Registration failed. Please try again."
                    onFailure(errorMessage)
                }
            }
    }

    fun validateInputs(email: String, password: String, confirmPassword: String): String? {
        return when {
            email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> "Please fill out all fields."
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Please enter a valid email address."
            password != confirmPassword -> "Passwords do not match."
            password.length < 6 -> "Password should be at least 6 characters long."
            else -> null
        }
    }
}
