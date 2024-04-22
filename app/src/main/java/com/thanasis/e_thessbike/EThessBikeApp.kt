package com.thanasis.e_thessbike

//import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import androidx.activity.compose.BackHandler
import androidx.annotation.StyleRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import com.thanasis.e_thessbike.ui.components.MenuDrawer
import com.thanasis.e_thessbike.ui.screens.LoginScreen
import com.thanasis.e_thessbike.ui.screens.RegisterScreen

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
    val TAG: String? = SignUpViewModel::class.simpleName

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = EThessBikeApp.Register.name) {
            composable(EThessBikeApp.Register.name) {
                BackHandler(true) {}
                RegisterScreen(navController, db, roomDb)
            }
            composable(EThessBikeApp.Login.name) {
                BackHandler(true) {}
                LoginScreen(navController, db, roomDb)
            }
            composable(EThessBikeApp.Home.name) {
                MenuDrawer(navController, selectedIndex = "home", db, roomDb, darkTheme, onThemeUpdated)
            }
            composable(EThessBikeApp.Settings.name) {
                MenuDrawer(navController, selectedIndex = "settings", db, roomDb, darkTheme, onThemeUpdated)
            }
            composable(EThessBikeApp.Profile.name) {
                MenuDrawer(navController, selectedIndex = "profile", db, roomDb, darkTheme, onThemeUpdated)
            }
            composable(EThessBikeApp.EditInfo.name)  {
                MenuDrawer(navController, selectedIndex = "editInfo", db, roomDb, darkTheme, onThemeUpdated)
            }
        }
    }
}

