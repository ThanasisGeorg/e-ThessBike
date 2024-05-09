package com.thanasis.e_thessbike.backend.changePw

data class ChangePasswordUIState (
    var newPassword: String = "",
    var confirmNewPassword: String = ""
)