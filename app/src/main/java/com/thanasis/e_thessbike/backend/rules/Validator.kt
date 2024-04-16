package com.thanasis.e_thessbike.backend.rules

object Validator {
    fun validateFirstName(fName: String): Boolean {
        return fName.isNotEmpty() && fName.length >= 4
    }

    fun validateLastName(lName: String): Boolean {
        return lName.isNotEmpty() && lName.length >= 4
    }

    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        return email.matches(emailRegex)
    }

    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 4
    }

    fun validateConditionsAndPrivacy(statusValue: Boolean): Boolean {
        return statusValue
    }

}