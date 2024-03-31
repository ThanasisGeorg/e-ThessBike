package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.ui.components.EditButton
import com.thanasis.e_thessbike.ui.components.ProfileCard
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileInit(navController: NavHostController, value: String) {
    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        },
        floatingActionButton = {
            EditButton()
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 77.dp)
        ) {
            ProfileCard()
        }
    }
}

@Preview
@Composable
fun ProfileInitPreview() {
    val navController = rememberNavController()
    ProfileInit(navController, "Profile")
}
