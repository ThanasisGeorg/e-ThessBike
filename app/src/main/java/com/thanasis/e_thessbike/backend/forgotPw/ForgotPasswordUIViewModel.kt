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
    fun onEvent(event: ForgotPasswordUIEvent, navHostController: NavHostController, db: FirebaseFirestore, context: Context): Int {
        when (event) {
            is ForgotPasswordUIEvent.EmailChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    email = event.email
                )

                val emailResult = Validator.validateExistingEmail(
                    email = forgotPasswordUIState.value.email
                )

                return if (emailResult) 1
                else -1
            }
            is ForgotPasswordUIEvent.NewPasswordChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    newPassword = event.newPassword
                )

                val newPasswordResult = Validator.validateNewPassword(
                    newPassword = forgotPasswordUIState.value.newPassword
                )

                return if (newPasswordResult) 2
                else -2
            }
            is ForgotPasswordUIEvent.ConfirmNewPasswordChanged -> {
                forgotPasswordUIState.value = forgotPasswordUIState.value.copy(
                    confirmNewPassword = event.confirmNewPassword
                )

                val confirmNewPasswordResult = Validator.validateConfirmNewPassword(
                    newPassword = forgotPasswordUIState.value.newPassword,
                    confirmNewPassword = forgotPasswordUIState.value.confirmNewPassword
                )

                if (confirmNewPasswordResult) allValidationsPassed.value = true
                else return -3
            }
            is ForgotPasswordUIEvent.ApplyBtnClicked -> {
                if (allValidationsPassed.value) {
                    changePassword(navHostController, db, context)
                }
            }
        }

        return 0
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
                        Toast.makeText(context, "Successful change of password", Toast.LENGTH_SHORT).show()
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