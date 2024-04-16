package com.thanasis.e_thessbike.backend.login

data class LoginUIState (
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false
)