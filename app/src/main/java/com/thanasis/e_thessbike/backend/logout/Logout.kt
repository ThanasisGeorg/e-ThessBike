package com.thanasis.e_thessbike.backend.logout

import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase

fun logout(navController: NavHostController, userLoggedIn: Array<String>, roomDb: AppDatabase, onClick: () -> Unit) {
    val settingsDao = roomDb.settingsDao()

    if (settingsDao.getTheme(userLoggedIn[1]) == "light") {
       onClick()
    }

    navController.navigate(EThessBikeApp.Login.name)
}