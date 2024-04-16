package com.thanasis.e_thessbike.backend.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.rules.Verifier
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

class LoginViewModel: ViewModel() {
    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationsPassed = mutableStateOf(false)
    private val TAG = SignUpViewModel::class.simpleName

    fun onEvent(event: LoginUIEvent, navHostController: NavHostController){
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is LoginUIEvent.LoginBtnClicked -> {
                login(navHostController)
            }
            else -> {}
        }
        verifyDataWithRules()
    }

    private fun login(navHostController: NavHostController) {
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
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