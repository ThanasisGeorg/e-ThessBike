package com.thanasis.e_thessbike.backend.forgotPw

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.getDocuments
import com.thanasis.e_thessbike.backend.rules.Validator
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ForgotPasswordUIViewModel: ViewModel() {
    private var forgotPasswordUIState = mutableStateOf(ForgotPasswordUIState())
    private var allValidationsPassed = mutableStateOf(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun onEvent(event: ForgotPasswordUIEvent, navHostController: NavHostController, db: FirebaseFirestore, context: Context) {
        validateWithRules()
        when (event) {
            is ForgotPasswordUIEvent.EmailChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    email = event.email
                )
            }
            is ForgotPasswordUIEvent.NewPasswordChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    newPassword = event.newPassword
                )
            }
            is ForgotPasswordUIEvent.ConfirmNewPasswordChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    confirmNewPassword = event.confirmNewPassword
                )
            }
            is ForgotPasswordUIEvent.ApplyBtnClicked -> {
                if (allValidationsPassed.value) {
                    changePassword(navHostController, db, context)
                } else {
                    Toast.makeText(context, "*Enter an existing email\n*Enter a valid and different password from the old one\n*Re-enter correctly your new password", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun changePassword(navHostController: NavHostController, db: FirebaseFirestore, context: Context) {
        val task = getDocuments("users").getCompleted()

        for (i in task.documents.indices) {
            if (task.documents[i].getString("email").equals(forgotPasswordUIState.value.email)) {
                db.collection("users")
                    .document(task.documents[i].id)
                    .update("password", forgotPasswordUIState.value.newPassword.hashCode().toString())
                    .addOnSuccessListener {
                        navHostController.navigate(EThessBikeApp.Login.name)
                    }
                    .addOnFailureListener{
                        Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun validateWithRules() {
        val emailResult = Validator.validateExistingEmail(
            email = forgotPasswordUIState.value.email
        )

        val newPasswordResult = Validator.validateNewPassword(
            newPassword = forgotPasswordUIState.value.newPassword
        )

        val confirmNewPasswordResult = Validator.validateConfirmNewPassword(
            newPassword = forgotPasswordUIState.value.newPassword,
            confirmNewPassword = forgotPasswordUIState.value.confirmNewPassword
        )

        allValidationsPassed.value = emailResult && newPasswordResult && confirmNewPasswordResult
    }
}