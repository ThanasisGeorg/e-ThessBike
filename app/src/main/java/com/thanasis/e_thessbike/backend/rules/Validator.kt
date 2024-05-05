package com.thanasis.e_thessbike.backend.rules

import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

object Validator {
    private val TAG = SignUpViewModel::class.simpleName

    fun validateBrandName(brandName: String): Boolean {
        return brandName.isNotEmpty() && brandName.length >= 4
    }

    fun validateColor(color: String): Boolean {
        return color.isNotEmpty() && (color == "blue" || color == "Blue" || color == "red" || color == "Red" || color == "black" || color == "Black" || color == "green" || color == "Green")
    }

    fun validateLocation(location: String): Boolean {
        return location.isNotEmpty() && (location == "Sikies" || location == "Neapoli" || location == "Stavroupoli" || location == "Evosmos" || location == "Polichni" || location == "Thessaloniki" || location == "Kalamaria" || location == "Meteora")
    }

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