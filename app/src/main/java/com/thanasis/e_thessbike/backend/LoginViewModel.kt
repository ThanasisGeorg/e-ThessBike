package com.thanasis.e_thessbike.backend

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.thanasis.e_thessbike.backend.rules.Validator

class LoginViewModel: ViewModel() {
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationsPassed = mutableStateOf(false)
    private val TAG = LoginViewModel::class.simpleName

    fun onEvent(event: UIEvent){
        validateDataWithRules()
        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is UIEvent.ConditionsAndPrivacyClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    conditionsAndPrivacyAccepted = event.status
                )
                printState()
            }
            is UIEvent.RegisterBtnClicked -> {
                signUp()
            }
            else -> {}
        }
    }

    private fun signUp(){
        val account = Account()
        Log.d(TAG, "Inside_signUp")
        printState()

        account.setFirstName(fName = registrationUIState.value.firstName)
        account.setLastName(lName = registrationUIState.value.lastName)
        account.setEmail(email = registrationUIState.value.email)

        createUser(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val conditionsAndPrivacyResult = Validator.validateConditionsAndPrivacy(
            statusValue = registrationUIState.value.conditionsAndPrivacyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")
        Log.d(TAG, "conditionsAndPrivacy = $conditionsAndPrivacyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult,
            lastNameError = lNameResult,
            emailError = emailResult,
            passwordError = passwordResult,
            conditionsAndPrivacyError = conditionsAndPrivacyResult
        )

        allValidationsPassed.value = fNameResult && lNameResult && emailResult && passwordResult && conditionsAndPrivacyResult
    }

    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun createUser(email: String, password: String){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }
}
