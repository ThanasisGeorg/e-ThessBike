package com.thanasis.e_thessbike.backend.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.initLocalDB
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.asDeferred

class LoginViewModel: ViewModel() {
    private var loginUIState = mutableStateOf(LoginUIState())
    private var userLoggedIn = arrayOf("", "")
    private var allValidationsPassed = mutableStateOf(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun onEvent(event: LoginUIEvent, navHostController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, context: Context): Array<String> {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            is LoginUIEvent.LoginBtnClicked -> {
                val document = getDocument(db, "users").getCompleted()

                userLoggedIn = verifyData(document)

                if (allValidationsPassed.value) {
                    initLocalDB(userLoggedIn, roomDb)
                    navHostController.navigate(EThessBikeApp.Home.name)
                } else Toast.makeText(context, "Login failed. Incorrect email or password", Toast.LENGTH_LONG).show()
            }
        }

        return userLoggedIn
    }

    fun getDocument(db: FirebaseFirestore, collectionName: String): Deferred<QuerySnapshot> {
        val task = db.collection(collectionName)
            .get()
            .asDeferred()

        runBlocking { task.await() }

        return task
    }

    private fun verifyData(document: QuerySnapshot): Array<String> {
        val emailResult = loginUIState.value.email
        val passwordResult = loginUIState.value.password.hashCode().toString()
        val userLoggedIn = arrayOf("", "")

        for (i in document.documents.indices) {
            if (document.documents[i].getString("email").equals(emailResult) && document.documents[i].getString("password").equals(passwordResult)) {
                userLoggedIn[0] = i.toString()
                userLoggedIn[1] = emailResult
                allValidationsPassed.value = true
                break
            }
        }

        return userLoggedIn
    }
}