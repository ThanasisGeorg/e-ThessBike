package com.thanasis.e_thessbike.backend.rules

import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

object Verifier {
    private val TAG = SignUpViewModel::class.simpleName

    fun verifyEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        return email.matches(emailRegex)
    }

    fun verifyPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 4
    }
}