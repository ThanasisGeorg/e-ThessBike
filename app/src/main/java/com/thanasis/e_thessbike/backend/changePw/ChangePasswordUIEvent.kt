package com.thanasis.e_thessbike.backend.changePw

sealed class ChangePasswordUIEvent {
    data class NewPasswordChanged(val newPassword: String): ChangePasswordUIEvent()
    data class ConfirmNewPasswordChanged(val confirmNewPassword: String): ChangePasswordUIEvent()

    data object ApplyBtnClicked: ChangePasswordUIEvent()
}