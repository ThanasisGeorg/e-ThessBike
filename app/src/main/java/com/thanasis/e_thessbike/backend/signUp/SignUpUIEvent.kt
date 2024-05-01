package com.thanasis.e_thessbike.backend.signUp

sealed class SignUpUIEvent {
    data class FirstNameChanged(val firstName: String): SignUpUIEvent()
    data class LastNameChanged(val lastName: String): SignUpUIEvent()
    data class EmailChanged(val email: String): SignUpUIEvent()
    data class PasswordChanged(val password: String): SignUpUIEvent()
    data class ConditionsAndPrivacyClicked(val status: Boolean): SignUpUIEvent()

    data object RegisterBtnClicked: SignUpUIEvent()
    data object ApplyBtnClicked: SignUpUIEvent()
}