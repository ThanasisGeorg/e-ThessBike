package com.thanasis.e_thessbike.backend.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.Account
import com.thanasis.e_thessbike.backend.rules.Validator

class SignUpViewModel: ViewModel() {
    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidationsPassed = mutableStateOf(false)
    private val TAG = SignUpViewModel::class.simpleName

    fun onEvent(event: SignUpUIEvent, navHostController: NavHostController){
        validateDataWithRules()
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignUpUIEvent.ConditionsAndPrivacyClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    conditionsAndPrivacyAccepted = event.status
                )
                validateDataWithRules()
                printState()
            }
            is SignUpUIEvent.RegisterBtnClicked -> {
                signUp(navHostController)
            }
            else -> {}
        }
    }

    private fun signUp(navHostController: NavHostController){
        val account = Account()
        Log.d(TAG, "Inside_signUp")
        printState()

        account.setFirstName(fName = signUpUIState.value.firstName)
        account.setLastName(lName = signUpUIState.value.lastName)
        account.setEmail(email = signUpUIState.value.email)

        createUser(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password,
            navHostController
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = signUpUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = signUpUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )

        val conditionsAndPrivacyResult = Validator.validateConditionsAndPrivacy(
            statusValue = signUpUIState.value.conditionsAndPrivacyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")
        Log.d(TAG, "conditionsAndPrivacyResult = $conditionsAndPrivacyResult")

        signUpUIState.value = signUpUIState.value.copy(
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
        Log.d(TAG, signUpUIState.value.toString())
    }

    private fun createUser(email: String, password: String, navHostController: NavHostController){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                if (it.isSuccessful) navHostController.navigate(EThessBikeApp.Home.name)
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }
}
