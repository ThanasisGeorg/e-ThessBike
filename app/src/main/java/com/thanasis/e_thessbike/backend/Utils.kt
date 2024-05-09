package com.thanasis.e_thessbike.backend

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.asDeferred

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("UnrememberedMutableState")
fun initInfo(collectionName: String): QuerySnapshot {
    return getDocuments(collectionName).getCompleted()
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
    val indexesOfMyBikes = getIndexesOfMyBikes(userLoggedIn)
    val task = getDocuments("bikes").getCompleted()
    val db = Firebase.firestore

    db.collection("bikes")
        .document(task.documents[indexesOfMyBikes[index]].id)
        .delete()
        .addOnFailureListener{
            Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show()
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun deleteAccount(userLoggedIn: Array<String>, context: Context) {
    val usersTask = getDocuments("users").getCompleted()
    val usersInfoTask = getDocuments("users_info").getCompleted()
    val bikeTask = getDocuments("bikes").getCompleted()
    val indexesOfMyBikes = getIndexesOfMyBikes(userLoggedIn)
    val db = Firebase.firestore

    runBlocking {
        db.collection("users")
            .document(usersTask.documents[userLoggedIn[0].toInt()].id)
            .delete()
            .asDeferred()
            .await()

        db.collection("users_info")
            .document(usersInfoTask.documents[userLoggedIn[0].toInt()].id)
            .delete()
            .asDeferred()
            .await()

        for (i in indexesOfMyBikes.indices) {
            db.collection("bikes")
                .document(bikeTask.documents[indexesOfMyBikes[i]].id)
                .delete()
                .asDeferred()
                .await()
        }
    }
}

