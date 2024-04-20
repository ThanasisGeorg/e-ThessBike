package com.thanasis.e_thessbike.backend.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.initInfo
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

class LoginViewModel: ViewModel() {
    private var loginUIState = mutableStateOf(LoginUIState())
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
                initInfo("users_info", 0, "name")
                initInfo("users_info", 0, "surname")
                initInfo("users_info", 0, "email")
            }
            else -> {}
        }
    }

    private fun login(navHostController: NavHostController, db: FirebaseFirestore) {
        db.collection("users")
            .get().addOnSuccessListener { document ->
                Log.d(TAG, "Inside email thing")
                Log.d(TAG, "email: ${document.documents[0].getString("email")}")
                if (verifyData(document)) {
                    navHostController.navigate(EThessBikeApp.Home.name)
                }
            }
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, loginUIState.value.toString())
    }

    private fun verifyData(document: QuerySnapshot): Boolean {
        val emailResult = loginUIState.value.email
        val password = loginUIState.value.password
        return document.documents[0].getString("email")?.equals(emailResult) == true && password == "123456"
    }
}