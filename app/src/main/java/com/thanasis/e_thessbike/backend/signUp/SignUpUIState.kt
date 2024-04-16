package com.thanasis.e_thessbike.backend.signUp

data class SignUpUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var conditionsAndPrivacyAccepted: Boolean = false,

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,
    var conditionsAndPrivacyError: Boolean = false
)