package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.ui.components.AddButton
import com.thanasis.e_thessbike.ui.components.EditButton
import com.thanasis.e_thessbike.ui.components.LogoutButton
import com.thanasis.e_thessbike.ui.components.ProfileCard
import com.thanasis.e_thessbike.ui.components.RemoveButton
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileInit(navController: NavHostController, value: String) {
    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        },
        floatingActionButton = {
            Row {
                EditButton()
                Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                LogoutButton(navController)
            }
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 77.dp)
        ) {
            ProfileCard()
            Spacer(modifier = Modifier.height(15.dp))
            Row {
                AddButton()
                Spacer(modifier = Modifier.padding(55.dp))
                RemoveButton()
            }
        }
    }
}

@Preview
@Composable
fun ProfileInitPreview() {
    val navController = rememberNavController()
    ProfileInit(navController, "Profile")
}
