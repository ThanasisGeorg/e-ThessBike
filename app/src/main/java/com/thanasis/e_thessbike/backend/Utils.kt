package com.thanasis.e_thessbike.backend

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.rules.Validator
import com.thanasis.e_thessbike.backend.signUp.SignUpUIEvent
import com.thanasis.e_thessbike.backend.signUp.SignUpUIState
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.asDeferred

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
fun initInfo(collectionName: String, fieldName: String): QuerySnapshot? {
    when (fieldName) {
        "name" -> {
            return getDocuments(collectionName).getCompleted()
        }
        "surname" -> {
            return getDocuments(collectionName).getCompleted()
        }
        "email" -> {
            return getDocuments(collectionName).getCompleted()
        }
    }
    return null
}

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
fun updateInfo(db: FirebaseFirestore, collectionName: String, numOfDocument: Int, fieldName: String, value: String): QuerySnapshot? {
    val task = db.collection(collectionName)
        .document(getDocuments(collectionName).getCompleted().documents[numOfDocument].id)
        .update(fieldName, value)
        .asDeferred()

    runBlocking { task.await() }

    return null
}

fun getDocuments(collectionName: String): Deferred<QuerySnapshot> {
    val db = Firebase.firestore
    val TAG = SignUpViewModel::class.simpleName

    val task = db.collection(collectionName)
        .get()
        .addOnSuccessListener {}
        .addOnFailureListener { e ->
            Log.d(TAG, "Operation failed", e)
        }
        .asDeferred()

    runBlocking { task.await() }

    return task
}

fun onEditEvent(event: SignUpUIEvent, signUpUIState: MutableState<SignUpUIState>, navHostController: NavHostController, db: FirebaseFirestore, context: Context, userLoggedIn: Array<String>) {
    val allValidationsPassed = validateData(signUpUIState)

    when (event) {
        is SignUpUIEvent.FirstNameChanged -> {
            signUpUIState.value = signUpUIState.value.copy(
                firstName = event.firstName
            )
            Log.d(TAG, "FirstName: ${signUpUIState.value.firstName}")
        }
        is SignUpUIEvent.LastNameChanged -> {
            signUpUIState.value = signUpUIState.value.copy(
                lastName = event.lastName
            )
            Log.d(TAG, "LastName: ${signUpUIState.value.lastName}")
        }
        is SignUpUIEvent.ApplyBtnClicked -> {
            if (allValidationsPassed) {
                updateInfo(db, "users_info", userLoggedIn[0].toInt(), "name", signUpUIState.value.firstName)
                updateInfo(db, "users_info", userLoggedIn[0].toInt(), "surname", signUpUIState.value.lastName)

                navHostController.navigate(EThessBikeApp.Profile.name)
            }
            else Toast.makeText(context, "Some fields are completed incorrectly", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}

fun validateData(signUpUIState: MutableState<SignUpUIState>): Boolean {
    val fNameResult = Validator.validateFirstName(
        fName = signUpUIState.value.firstName
    )

    val lNameResult = Validator.validateLastName(
        lName = signUpUIState.value.lastName
    )

    Log.d(TAG, "Flag: ${fNameResult && lNameResult}")

    return fNameResult && lNameResult
}
@OptIn(ExperimentalCoroutinesApi::class)
fun getIndexesOfMyBikes(userLoggedIn: Array<String>): ArrayList<Int> {
    val task = getDocuments("bikes").getCompleted()
    val indexes = arrayListOf<Int>()

    for (i in task.documents.indices) {
        if (task.documents[i].getString("email") == userLoggedIn[1]) {
            indexes.add(i)
        }
    }

    return indexes
}

@OptIn(ExperimentalCoroutinesApi::class)
fun getIndexesOfAvailableBikes(): ArrayList<Int> {
    val task = getDocuments("bikes").getCompleted()
    val indexes = arrayListOf<Int>()

    for (i in task.documents.indices) {
        indexes.add(i)
    }

    return indexes
}

@OptIn(ExperimentalCoroutinesApi::class)
fun getIndexesOfSpecificBikes(brandName: String): ArrayList<Int> {
    val task = getDocuments("bikes").getCompleted()
    val indexes = arrayListOf<Int>()

    for (i in task.documents.indices) {
        if (task.documents[i].getString("brand_name")?.contains(brandName) == true) {
            indexes.add(i)
        }
    }

    return indexes
}

@OptIn(ExperimentalCoroutinesApi::class)
fun removeBike(userLoggedIn: Array<String>, index: Int, context: Context) {
    val indexesOfBikes = getIndexesOfMyBikes(userLoggedIn)
    val task = getDocuments("bikes").getCompleted()
    val db = Firebase.firestore

    db.collection("bikes")
        .document(task.documents[indexesOfBikes[index]].id)
        .delete()
        .addOnFailureListener{
            Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
        }
}

