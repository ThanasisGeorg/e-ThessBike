package com.thanasis.e_thessbike

//import androidx.room.Room
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.theme.EThessBikeTheme

class MainActivity : ComponentActivity() {
    private var auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme()
            var darkTheme by remember {mutableStateOf(isSystemInDarkTheme)}
            val roomDb = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "Settings"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

            EThessBikeTheme(darkTheme) {
                MainApp(
                    db,
                    roomDb,
                    darkTheme = darkTheme,
                    onThemeUpdated = { darkTheme = !darkTheme }
                )
            }
        }
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }
}