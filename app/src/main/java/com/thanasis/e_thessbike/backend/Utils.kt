package com.thanasis.e_thessbike.backend

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.asDeferred

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
fun initInfo(collectionName: String, numOfDocument: Int, fieldName: String): QuerySnapshot? {
    val account = mutableStateOf(Account())
    //val TAG: String? = SignUpViewModel::class.simpleName

    when (fieldName) {
        "name" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    account.value = account.value.copy(firstName = it)
                }
            }

            return getData(collectionName, numOfDocument, fieldName, onSuccess).getCompleted()
        }
        "surname" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    account.value = account.value.copy(lastName = it)
                }
            }

            return getData(collectionName, numOfDocument, fieldName, onSuccess).getCompleted()
        }
        "email" -> {
            val onSuccess: (String?) -> Unit = {
                if (it != null) {
                    account.value = account.value.copy(email = it)
                }
            }

            return getData(collectionName, numOfDocument, fieldName, onSuccess).getCompleted()
        }
    }
    return null
}

fun getData(collectionName: String, numOfDocument: Int, fieldName: String, onSuccess: (String?) -> Unit): Deferred<QuerySnapshot> {
    val db = Firebase.firestore
    val TAG = SignUpViewModel::class.simpleName

    val task = db.collection(collectionName)
        .get()
        .addOnSuccessListener {
            if (it != null) {
                onSuccess(it.documents[numOfDocument].getString(fieldName).toString())
            }
        }
        .addOnFailureListener { e ->
            Log.d(TAG, "Operation failed", e)
        }
        .asDeferred()

    runBlocking { task.await() }

    return task
}