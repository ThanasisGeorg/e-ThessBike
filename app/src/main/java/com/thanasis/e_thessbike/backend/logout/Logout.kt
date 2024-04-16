package com.thanasis.e_thessbike.backend.logout

import android.util.Log
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

fun logout(navController: NavHostController) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val TAG = SignUpViewModel::class.simpleName
    firebaseAuth.signOut()

    val authStateListener = FirebaseAuth.AuthStateListener {
        if (it.currentUser == null) {
            Log.d(TAG, "Inside logout success")
            navController.navigate(EThessBikeApp.Login.name)
        } else {
            Log.d(TAG, "Inside logout fail")
        }
    }

    firebaseAuth.addAuthStateListener(authStateListener)
}