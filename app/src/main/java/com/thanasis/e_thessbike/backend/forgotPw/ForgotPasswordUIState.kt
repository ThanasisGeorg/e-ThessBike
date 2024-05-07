package com.thanasis.e_thessbike.backend.forgotPw

data class ForgotPasswordUIState (
    var email: String = "",
    var newPassword: String = "",
    var confirmNewPassword: String = ""
)