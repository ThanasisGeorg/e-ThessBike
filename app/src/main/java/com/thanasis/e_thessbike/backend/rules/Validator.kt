package com.thanasis.e_thessbike.backend.rules

import android.content.ContentValues
import android.util.Log
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
        Log.d(ContentValues.TAG, "FirstName: $fName")
        return fName.isNotEmpty() && fName.length >= 4
    }

    fun validateLastName(lName: String): Boolean {
        Log.d(ContentValues.TAG, "LastName: $lName")
        return lName.isNotEmpty() && lName.length >= 4
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun validateEmail(email: String): Int {
        val emailRegex = Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\$")
        val task = getDocuments("users").getCompleted()

        if (email.isBlank() || email.isEmpty()) return -1

        if (!email.matches(emailRegex)) return -2

        for (i in task.documents.indices) {
            if (task.documents[i].getString("email").equals(email)) return -3
        }

        return 1
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
            if (!task.documents[i].getString("password").equals(newPassword.hashCode().toString()) && newPassword.length >= 8) {
                return true
            }
        }

        return false
    }

    fun validateConfirmNewPassword(newPassword: String, confirmNewPassword: String): Boolean {
        return confirmNewPassword == newPassword
    }
}