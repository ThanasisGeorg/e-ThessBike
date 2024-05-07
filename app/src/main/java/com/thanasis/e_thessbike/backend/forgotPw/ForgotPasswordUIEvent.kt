package com.thanasis.e_thessbike.backend.forgotPw

sealed class ForgotPasswordUIEvent {
    data class EmailChanged(val email: String): ForgotPasswordUIEvent()
    data class NewPasswordChanged(val newPassword: String): ForgotPasswordUIEvent()
    data class ConfirmNewPasswordChanged(val confirmNewPassword: String): ForgotPasswordUIEvent()

    data object ApplyBtnClicked: ForgotPasswordUIEvent()
}