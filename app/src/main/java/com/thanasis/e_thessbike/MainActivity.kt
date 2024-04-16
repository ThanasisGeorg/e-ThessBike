package com.thanasis.e_thessbike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.ui.theme.EThessBikeTheme

class MainActivity : ComponentActivity() {
    private var auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EThessBikeTheme {
                MainApp(db)
            }
        }
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }
}