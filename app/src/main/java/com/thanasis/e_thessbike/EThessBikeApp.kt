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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.components.MenuDrawer
import com.thanasis.e_thessbike.ui.screens.ForgotPasswordScreen
import com.thanasis.e_thessbike.ui.screens.PrivacyPolicyScreen
import com.thanasis.e_thessbike.ui.screens.TermsOfUseScreen
import com.thanasis.e_thessbike.ui.screens.loginScreen
import com.thanasis.e_thessbike.ui.screens.registerScreen

enum class EThessBikeApp(@StyleRes val title: Int) {
    Home(title = R.string.app_name),
    Profile(title = R.string.profile),
    Register(title = R.string.register),
    Login(title = R.string.login),
    ForgotPassword(title = R.string.forgot_password),
    Settings(title = R.string.settings),
    Search(title = R.string.search),
    EditInfo(title = R.string.edit_info),
    AddBike(title = R.string.add_bike),
    MyBikeList(title = R.string.bike_list),
    AllBikeList(title = R.string.available_bikes),
    TermsOfUse(title = R.string.terms_of_use),
    PrivacyPolicy(title = R.string.privacy_policy)
}

@Composable
fun MainApp(
    db: FirebaseFirestore,
    roomDb: AppDatabase,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    notificationService: NotificationService,
    navHostController: NavHostController
) {
    var userLoggedIn by remember { mutableStateOf(arrayOf("", "")) }

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController = navHostController, startDestination = EThessBikeApp.Login.name) {
            composable(EThessBikeApp.Register.name) {
                BackHandler(true) {}
                userLoggedIn = registerScreen(navHostController, db, roomDb)
            }
            composable(EThessBikeApp.Login.name) {
                BackHandler(true) {}
                userLoggedIn = loginScreen(navHostController, db, roomDb)
            }
            composable(EThessBikeApp.ForgotPassword.name) {
                ForgotPasswordScreen(navHostController, stringResource(id = R.string.forgot_password), db)
            }
            composable(EThessBikeApp.TermsOfUse.name) {
                TermsOfUseScreen(stringResource(id = R.string.terms_of_use))
            }
            composable(EThessBikeApp.PrivacyPolicy.name) {
                PrivacyPolicyScreen(stringResource(id = R.string.privacy_policy))
            }
            composable(EThessBikeApp.Home.name) {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "home",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.Settings.name) {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "settings",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.Profile.name) {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "profile",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.EditInfo.name)  {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "editInfo",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.Search.name)  {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "search",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.AddBike.name)  {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "add_bike",
                    db, roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService)
            }
            composable(EThessBikeApp.MyBikeList.name)  {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "my_bike_list",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
            composable(EThessBikeApp.AllBikeList.name)  {
                MenuDrawer(
                    navHostController,
                    selectedIndex = "available_bikes",
                    db,
                    roomDb,
                    darkTheme,
                    onThemeUpdated,
                    userLoggedIn,
                    notificationService
                )
            }
        }
    }
}