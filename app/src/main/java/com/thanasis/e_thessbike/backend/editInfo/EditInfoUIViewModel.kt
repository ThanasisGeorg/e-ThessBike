package com.thanasis.e_thessbike.backend.editInfo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.initInfo
import com.thanasis.e_thessbike.backend.rules.Validator
import com.thanasis.e_thessbike.backend.updateInfo

class EditInfoUIViewModel(var userLoggedIn: Array<String>): ViewModel() {
    val allValidationsPassed = mutableStateOf(true)
    val data = initInfo("users_info")
    private val editInfoUIState = mutableStateOf(EditInfoUIState(
        firstName = data.documents[userLoggedIn[0].toInt()].getString("name") ?: "",
        lastName = data.documents[userLoggedIn[0].toInt()].getString("surname") ?: ""
    ))

    fun onEditEvent(event: EditInfoUIEvent, navHostController: NavHostController, db: FirebaseFirestore, userLoggedIn: Array<String>) {
        when (event) {
            is EditInfoUIEvent.FirstNameChanged -> {
                Log.d(TAG, event.firstName)
                editInfoUIState.value = editInfoUIState.value.copy(
                    firstName = event.firstName
                )
                validateData(editInfoUIState)
            }
            is EditInfoUIEvent.LastNameChanged -> {
                Log.d(TAG, event.lastName)
                editInfoUIState.value = editInfoUIState.value.copy(
                    lastName = event.lastName
                )
                validateData(editInfoUIState)
            }
            is EditInfoUIEvent.ApplyBtnClicked -> {
                updateInfo(db, "users_info", userLoggedIn[0].toInt(), "name", editInfoUIState.value.firstName)
                updateInfo(db, "users_info", userLoggedIn[0].toInt(), "surname", editInfoUIState.value.lastName)

                navHostController.navigate(EThessBikeApp.Profile.name)
            }
        }
    }

    private fun validateData(editInfoUIState: MutableState<EditInfoUIState>) {
        val fNameResult = Validator.validateFirstName(
            fName = editInfoUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = editInfoUIState.value.lastName
        )

        allValidationsPassed.value = fNameResult && lNameResult
        Log.d(TAG, "All validations passed: ${allValidationsPassed.value}")
    }
}