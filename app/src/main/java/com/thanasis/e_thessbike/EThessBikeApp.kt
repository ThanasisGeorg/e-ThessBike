package com.thanasis.e_thessbike

import androidx.activity.compose.BackHandler
import androidx.annotation.StyleRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.ui.components.MenuDrawer
import com.thanasis.e_thessbike.ui.screens.LoginScreen
import com.thanasis.e_thessbike.ui.screens.RegisterScreen

enum class EThessBikeApp(@StyleRes val title: Int) {
    Home(title = R.string.app_name),
    Profile(title = R.string.profile),
    Register(title = R.string.register),
    Login(title = R.string.login),
    Settings(title = R.string.settings)
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navController, startDestination = EThessBikeApp.Register.name) {
            composable(EThessBikeApp.Register.name) {
                BackHandler(true) {}
                RegisterScreen(navController)
            }
            composable(EThessBikeApp.Login.name) {
                BackHandler(true) {}
                LoginScreen(navController)
            }
            composable(EThessBikeApp.Home.name) {
                MenuDrawer(navController, selectedIndex = "home")
            }
            composable(EThessBikeApp.Settings.name) {
                MenuDrawer(navController, selectedIndex = "settings")
            }
            composable(EThessBikeApp.Profile.name) {
                MenuDrawer(navController, selectedIndex = "profile")
            }
        }
    }
}

