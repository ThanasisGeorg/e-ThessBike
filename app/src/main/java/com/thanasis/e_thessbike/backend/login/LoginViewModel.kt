package com.thanasis.e_thessbike.backend.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.initAfterLogin
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

class LoginViewModel: ViewModel() {
    private var loginUIState = mutableStateOf(LoginUIState())
    private val TAG = SignUpViewModel::class.simpleName

    fun onEvent(event: LoginUIEvent, navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase){
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
                login(navHostController, db, roomDb)
            }
            else -> {}
        }
    }

    private fun login(navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase) {
        db.collection("users")
            .get().addOnSuccessListener { document ->
                Log.d(TAG, "Inside email thing")
                Log.d(TAG, "email: ${document.documents[0].getString("email")}")
                val userLoggedIn = verifyData(document)
                if (!userLoggedIn.isNullOrEmpty()) {
                    initAfterLogin(userLoggedIn, roomDb)
                    navHostController.navigate(EThessBikeApp.Home.name)
                } else {
                    Log.d(TAG, "Something went wrong")
                }
            }
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, loginUIState.value.toString())
    }

    private fun verifyData(document: QuerySnapshot): String? {
        val emailResult = loginUIState.value.email
        val passwordResult = loginUIState.value.password

        for (i in document.documents.indices) {
            if (document.documents[i].getString("email").equals(emailResult) && passwordResult == "123456") {
                return emailResult
            }
        }

        return null
    }
}