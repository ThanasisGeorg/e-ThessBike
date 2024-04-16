package com.thanasis.e_thessbike.backend.login

sealed class LoginUIEvent {
    data class EmailChanged(val email: String): LoginUIEvent()
    data class PasswordChanged(val password: String): LoginUIEvent()

    data object LoginBtnClicked: LoginUIEvent()
}