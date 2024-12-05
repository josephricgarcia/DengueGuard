package com.example.mylogin.controller

import com.example.mylogin.model.AdminRegisterModel

class AdminRegisterController(private val model: AdminRegisterModel) {
    fun onRegister(
        email: String,
        password: String,
        confirmPassword: String,
        onValidationFailed: (String) -> Unit,
        onRegistrationSuccess: () -> Unit,
        onRegistrationFailure: (String) -> Unit
    ) {
        val validationError = model.validateInputs(email, password, confirmPassword)
        if (validationError != null) {
            onValidationFailed(validationError)
        } else {
            model.registerUser(email, password, onRegistrationSuccess, onRegistrationFailure)
        }
    }
}
