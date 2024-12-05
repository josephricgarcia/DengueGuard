package com.example.mylogin.controller

import com.example.mylogin.model.AdminLoginModel

class AdminLoginController(private val model: AdminLoginModel) {

    fun onLogin(
        email: String,
        password: String,
        onValidationFailed: (String) -> Unit,
        onLoginSuccess: () -> Unit,
        onLoginFailure: (String) -> Unit
    ) {
        val validationError = model.validateInputs(email, password)
        if (validationError != null) {
            onValidationFailed(validationError)
        } else {
            model.loginUser(email, password, onLoginSuccess, onLoginFailure)
        }
    }
}
