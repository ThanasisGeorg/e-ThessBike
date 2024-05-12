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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.theme.MyTheme
import com.thanasis.e_thessbike.ui.theme.Pink40
import com.thanasis.e_thessbike.ui.theme.Pink80
import com.thanasis.e_thessbike.ui.theme.Purple40
import com.thanasis.e_thessbike.ui.theme.Purple80
import com.thanasis.e_thessbike.ui.theme.PurpleGrey40
import com.thanasis.e_thessbike.ui.theme.PurpleGrey80
import com.thanasis.e_thessbike.ui.theme.ThemeManager

class MainActivity: ComponentActivity() {
    private val db = Firebase.firestore
    private val DarkColorScheme = darkColorScheme(
        primary = Purple80,
        secondary = PurpleGrey80,
        tertiary = Pink80
    )
    private val LightColorScheme = lightColorScheme(
        primary = Purple40,
        secondary = PurpleGrey40,
        tertiary = Pink40
    )


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val currentTheme = ThemeManager.getCurrentTheme()
            val darkTheme = isSystemInDarkTheme()
            val roomDb = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "Settings"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
            val notificationService = NotificationService(this)
            val navHostController = rememberNavController()

            LaunchedEffect(key1 = true) {
                if (!postNotificationPermission.status.isGranted) {
                    postNotificationPermission.launchPermissionRequest()
                }
            }

            MaterialTheme(
                colorScheme =
                    when (currentTheme) {
                        is MyTheme.Light -> LightColorScheme
                        is MyTheme.Dark -> DarkColorScheme
                    }
            ) {
                MainApp(
                    db,
                    roomDb,
                    notificationService,
                    navHostController
                )
            }
        }
    }
}