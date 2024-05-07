package com.thanasis.e_thessbike.backend.rules

import com.thanasis.e_thessbike.backend.getDocuments
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

object Validator {
    private val TAG = SignUpViewModel::class.simpleName

    fun validateBrandName(brandName: String): Boolean {
        return brandName.isNotEmpty() && brandName.length >= 4
    }

    fun validateColor(color: String): Boolean {
        return color.isNotEmpty() && color.isNotBlank()
    }

    fun validateLocation(location: String): Boolean {
        return location.isNotEmpty() && location.isNotBlank()
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
    @OptIn(ExperimentalCoroutinesApi::class)
    fun validateExistingEmail(email: String): Boolean {
        val task = getDocuments("users").getCompleted()

        for (i in task.documents.indices) {
            if (task.documents[i].getString("email").equals(email)) {
                return true
            }
        }

        return false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun validateNewPassword(newPassword: String): Boolean {
        val task = getDocuments("users").getCompleted()

        for (i in task.documents.indices) {
            if (!task.documents[i].getString("password").equals(newPassword)) {
                return true
            }
        }

        return false
    }

    fun validateConfirmNewPassword(newPassword: String, confirmNewPassword: String): Boolean {
        return confirmNewPassword == newPassword
    }
}