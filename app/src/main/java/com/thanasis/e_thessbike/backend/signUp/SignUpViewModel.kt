package com.thanasis.e_thessbike.backend.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.Account
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.initAfterRegister
import com.thanasis.e_thessbike.backend.rules.Validator

class SignUpViewModel: ViewModel() {
    var signUpUIState = mutableStateOf(SignUpUIState())
    private var allValidationsPassed = mutableStateOf(false)
    private val TAG = SignUpViewModel::class.simpleName

    fun onEvent(event: SignUpUIEvent, navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase){
        validateDataWithRules()
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                //printState()
            }
            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                //printState()
            }
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                //printState()
            }
            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                //printState()
            }
            is SignUpUIEvent.ConditionsAndPrivacyClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    conditionsAndPrivacyAccepted = event.status
                )
                validateDataWithRules()
                //printState()
            }
            is SignUpUIEvent.RegisterBtnClicked -> {
                signUp(navHostController, db, roomDb)
            }
            else -> {}
        }
    }

    private fun signUp(navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase){
        val account = mutableStateOf(Account())
        //Log.d(TAG, "Inside_signUp")
        //printState()

        account.value = account.value.copy(
            signUpUIState.value.firstName,
            signUpUIState.value.lastName,
            signUpUIState.value.email
        )

        createUser(db, navHostController, roomDb)
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

    private fun createUser(db: FirebaseFirestore, navHostController: NavHostController, roomDb: AppDatabase) {
        val userInfo = hashMapOf(
            "name" to signUpUIState.value.firstName,
            "surname" to signUpUIState.value.lastName,
            "email" to signUpUIState.value.email
        )

        val user = hashMapOf(
            "email" to signUpUIState.value.email
        )

        db.collection("users_info")
            .add(userInfo)
            .addOnSuccessListener { userInfoReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${userInfoReference.id}")
                db.collection("users")
                    .add(user)
                initAfterRegister(user["email"].toString(), roomDb)
                navHostController.navigate(EThessBikeApp.Home.name)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
