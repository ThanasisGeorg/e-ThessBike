package com.thanasis.e_thessbike.backend.signUp

data class SignUpUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var conditionsAndPrivacyAccepted: Boolean = false,

    var firstNameError: Boolean = true,
    var lastNameError: Boolean = true,
    var emailError: Boolean = true,
    var passwordError: Boolean = true,
    var conditionsAndPrivacyError: Boolean = true
)
