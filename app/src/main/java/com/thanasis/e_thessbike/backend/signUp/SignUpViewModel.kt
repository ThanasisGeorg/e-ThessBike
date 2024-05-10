package com.thanasis.e_thessbike.backend.signUp

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.Account
import com.thanasis.e_thessbike.backend.login.LoginViewModel
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.initUser
import com.thanasis.e_thessbike.backend.rules.Validator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidationsPassed = mutableStateOf(false)
    private var userLoggedIn = arrayOf("", "")

    fun onEvent(event: SignUpUIEvent, navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, context: Context): Array<String> {
        validateDataWithRules(context)
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
            }
            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
            }
            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
            }
            is SignUpUIEvent.ConditionsAndPrivacyClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    conditionsAndPrivacyAccepted = event.status
                )
                validateDataWithRules(context)
            }
            is SignUpUIEvent.RegisterBtnClicked -> {
                if (Validator.validateEmail(email = signUpUIState.value.email) == -2) {
                    Toast.makeText(context, "This email does not match a proper email sequence", Toast.LENGTH_LONG).show()
                } else if (Validator.validateEmail(email = signUpUIState.value.email) == -3) {
                    Toast.makeText(context, "This email is already in use", Toast.LENGTH_LONG).show()
                } else {
                    userLoggedIn = signUp(db)
                    initUser(userLoggedIn, roomDb)
                    navHostController.navigate(EThessBikeApp.Home.name)
                }
            }
            else -> {}
        }

        return userLoggedIn
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun signUp(db: FirebaseFirestore): Array<String> {
        val account = mutableStateOf(Account())

        account.value = account.value.copy(
            signUpUIState.value.firstName,
            signUpUIState.value.lastName,
            signUpUIState.value.email,
            signUpUIState.value.password
        )

        createUser(db)

        val document = LoginViewModel().getDocument(db, "users_info").getCompleted()

        return verifyData(document)
    }

    private fun verifyData(document: QuerySnapshot): Array<String> {
        val emailResult = signUpUIState.value.email
        val userLoggedIn = arrayOf("", "")

        for (i in document.documents.indices) {
            if (document.documents[i].getString("email").equals(emailResult)) {
                userLoggedIn[0] = i.toString()
                userLoggedIn[1] = emailResult
            }
        }

        return userLoggedIn
    }

    private fun validateDataWithRules(context: Context) {
        val emailFlag = mutableStateOf(false)

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

        emailFlag.value = emailResult > 0

        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = fNameResult,
            lastNameError = lNameResult,
            emailError = emailFlag.value,
            passwordError = passwordResult,
            conditionsAndPrivacyError = conditionsAndPrivacyResult
        )

        allValidationsPassed.value = fNameResult && lNameResult && emailFlag.value && passwordResult && conditionsAndPrivacyResult
    }

    private fun createUser(db: FirebaseFirestore) {
        val userInfo = hashMapOf(
            "name" to signUpUIState.value.firstName,
            "surname" to signUpUIState.value.lastName,
            "email" to signUpUIState.value.email
        )

        val user = hashMapOf(
            "email" to signUpUIState.value.email,
            "password" to signUpUIState.value.password.hashCode().toString()
        )

        runBlocking {
            db.collection("users")
                .add(user)
                .await()

            db.collection("users_info")
                .add(userInfo)
                .await()
        }
    }
}
