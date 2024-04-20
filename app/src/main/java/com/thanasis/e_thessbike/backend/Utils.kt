package com.thanasis.e_thessbike.backend

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun initInfo(collectionName: String, numOfDocument: Int, fieldName: String): MutableState<Account> {
    val account = mutableStateOf(Account())
    val TAG: String? = SignUpViewModel::class.simpleName

    when (fieldName) {
        "name" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    Log.d(TAG, "it: $it")
                    account.value.firstName = it
                }
            }

            runBlocking {
                launch {
                    getData(collectionName, numOfDocument, fieldName, onSuccess)
                }
            }

            return account
        }
        "surname" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    Log.d(TAG, "it: $it")
                    account.value.lastName = it
                }
            }

            runBlocking {
                launch {
                    getData("users_info", 0, fieldName, onSuccess)
                }
            }

            return account
        }
        "email" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    Log.d(TAG, "it: $it")
                    account.value.email = it
                }
            }

            runBlocking {
                launch {
                    getData(collectionName, numOfDocument, fieldName, onSuccess)
                }
            }

            return account
        }
    }
    return account
}

suspend fun getData(collectionName: String, numOfDocument: Int, fieldName: String, onSuccess: (String?) -> Unit){
    val db = Firebase.firestore
    val TAG = SignUpViewModel::class.simpleName

    delay(500)
    db.collection(collectionName)
        .get()
        .addOnSuccessListener {
            if (it != null) {
                Log.d(TAG, "Inside setInfo()")
                Log.d(TAG, "result: ${it.documents[numOfDocument].getString(fieldName).toString()}")
                onSuccess(it.documents[numOfDocument].getString(fieldName).toString())
            }
        }
        .addOnFailureListener { e ->
            Log.d(TAG, "Operation failed", e)
        }
}