package com.thanasis.e_thessbike.backend.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.backend.rules.Verifier
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

class LoginViewModel: ViewModel() {
    private var loginUIState = mutableStateOf(LoginUIState())
    var allValidationsPassed = mutableStateOf(false)
    private val TAG = SignUpViewModel::class.simpleName

    fun onEvent(event: LoginUIEvent, navHostController: NavHostController, db: FirebaseFirestore){
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
                //printState()
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
                //printState()
            }
            is LoginUIEvent.LoginBtnClicked -> {
                login(navHostController, db)
            }
            else -> {}
        }
        //verifyDataWithRules()
    }

    private fun login(navHostController: NavHostController, db: FirebaseFirestore) {
        val emailResult = loginUIState.value.email
        val passwordResult = loginUIState.value.password
        var email: Boolean = false
        var password: Boolean = false

        db.collection("users")
            .document("users")
            .get().addOnSuccessListener { document ->
                if (document.data?.equals(emailResult) == true) {
                    email = true
                }
            }
        Log.d(TAG, "Inside email thing...")
        Log.d(TAG, "email: $email")

        /*if (emailResult.equals(email) && passwordResult == "123456") {
            navHostController.navigate(EThessBikeApp.Home.name)
        }*/
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, loginUIState.value.toString())
    }

    private fun verifyDataWithRules() {
        val emailResult = Verifier.verifyEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Verifier.verifyPassword(
            password = loginUIState.value.password
        )

        Log.d(TAG, "Inside_verifyDataWithRules")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult,
            passwordError = passwordResult,
        )

        allValidationsPassed.value = emailResult && passwordResult
    }

}