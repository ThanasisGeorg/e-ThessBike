package com.thanasis.e_thessbike.backend.logout

import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp

fun logout(navController: NavHostController) {
    navController.navigate(EThessBikeApp.Login.name)
}