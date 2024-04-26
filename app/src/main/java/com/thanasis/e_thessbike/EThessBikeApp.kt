package com.thanasis.e_thessbike

import androidx.activity.compose.BackHandler
import androidx.annotation.StyleRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.components.MenuDrawer
import com.thanasis.e_thessbike.ui.screens.loginScreen
import com.thanasis.e_thessbike.ui.screens.registerScreen

enum class EThessBikeApp(@StyleRes val title: Int) {
    Home(title = R.string.app_name),
    Profile(title = R.string.profile),
    Register(title = R.string.register),
    Login(title = R.string.login),
    Settings(title = R.string.settings),
    EditInfo(title = R.string.edit_info)
}

@Composable
fun MainApp(db: FirebaseFirestore, roomDb: AppDatabase, darkTheme: Boolean, onThemeUpdated: () -> Unit) {
    val navController = rememberNavController()
    var userLoggedIn by remember { mutableStateOf(arrayOf("", "")) }

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = EThessBikeApp.Login.name) {
            composable(EThessBikeApp.Register.name) {
                BackHandler(true) {}
                userLoggedIn = registerScreen(navController, db, roomDb)
            }
            composable(EThessBikeApp.Login.name) {
                BackHandler(true) {}
                userLoggedIn = loginScreen(navController, db, roomDb)
            }
            composable(EThessBikeApp.Home.name) {
                MenuDrawer(navController, selectedIndex = "home", db, roomDb, darkTheme, onThemeUpdated, userLoggedIn)
            }
            composable(EThessBikeApp.Settings.name) {
                MenuDrawer(navController, selectedIndex = "settings", db, roomDb, darkTheme, onThemeUpdated, userLoggedIn)
            }
            composable(EThessBikeApp.Profile.name) {
                MenuDrawer(navController, selectedIndex = "profile", db, roomDb, darkTheme, onThemeUpdated, userLoggedIn)
            }
            composable(EThessBikeApp.EditInfo.name)  {
                MenuDrawer(navController, selectedIndex = "editInfo", db, roomDb, darkTheme, onThemeUpdated, userLoggedIn)
            }
        }
    }
}

