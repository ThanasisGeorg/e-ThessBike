package com.thanasis.e_thessbike.backend.logout

import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.roomAPI.Settings

fun logout(navController: NavHostController, userLoggedIn: Array<String>, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()

    settingsDao.deleteSettings(Settings(userId = userLoggedIn[1]))

    navController.navigate(EThessBikeApp.Login.name)
}