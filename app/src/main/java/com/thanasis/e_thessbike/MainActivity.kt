package com.thanasis.e_thessbike

//import androidx.room.Room
import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.theme.EThessBikeTheme

class MainActivity: ComponentActivity() {
    private val db = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isSystemInDarkTheme = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(isSystemInDarkTheme) }
            val roomDb = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "Settings"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
            val notificationService = NotificationService(this)
            val navHostController = rememberNavController()
            val configuration = LocalConfiguration.current

            LaunchedEffect(key1 = true) {
                if (!postNotificationPermission.status.isGranted) {
                    postNotificationPermission.launchPermissionRequest()
                }
            }

            EThessBikeTheme(darkTheme) {
                MainApp(
                    db,
                    roomDb,
                    darkTheme = darkTheme,
                    onThemeUpdated = {
                        darkTheme = !darkTheme
                        navHostController.navigate(EThessBikeApp.Settings.name)
                    },
                    notificationService,
                    navHostController,
                    configuration
                )
            }
        }
    }
}