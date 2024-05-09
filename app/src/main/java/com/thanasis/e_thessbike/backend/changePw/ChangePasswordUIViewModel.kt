package com.thanasis.e_thessbike.backend.changePw

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

class ChangePasswordUIViewModel: ViewModel() {
    private var changePasswordUIState = mutableStateOf(ChangePasswordUIState())
    private var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: ChangePasswordUIEvent, navHostController: NavHostController, db: FirebaseFirestore, userLoggedIn: Array<String>, context: Context): Int {
        when (event) {
            is ChangePasswordUIEvent.NewPasswordChanged -> {
                changePasswordUIState.value = changePasswordUIState.value.copy(
                    newPassword = event.newPassword
                )

                val newPasswordResult = Validator.validateNewPassword(
                    newPassword = changePasswordUIState.value.newPassword
                )

                return if (newPasswordResult) 1
                else -1
            }
            is ChangePasswordUIEvent.ConfirmNewPasswordChanged -> {
                changePasswordUIState.value = changePasswordUIState.value.copy(
                    confirmNewPassword = event.confirmNewPassword
                )

                val confirmNewPasswordResult = Validator.validateConfirmNewPassword(
                    newPassword = changePasswordUIState.value.newPassword,
                    confirmNewPassword = changePasswordUIState.value.confirmNewPassword
                )

                if (confirmNewPasswordResult) allValidationsPassed.value = true
                else return -2
            }
            is ChangePasswordUIEvent.ApplyBtnClicked -> {
                if (allValidationsPassed.value) {
                    changePassword(navHostController, db, userLoggedIn, context)
                } else return -3
            }
        }

        return 0
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun changePassword(navHostController: NavHostController, db: FirebaseFirestore, userLoggedIn: Array<String>, context: Context) {
        val task = getDocuments("users").getCompleted()

        for (i in task.documents.indices) {
            if (task.documents[i].getString("email").equals(userLoggedIn[1])) {
                db.collection("users")
                    .document(task.documents[i].id)
                    .update("password", changePasswordUIState.value.newPassword.hashCode().toString())
                    .addOnSuccessListener {
                        navHostController.navigate(EThessBikeApp.Settings.name)
                        Toast.makeText(context, "Successful change of password", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}