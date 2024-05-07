package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.ui.components.ButtonComp
import com.thanasis.e_thessbike.ui.components.EditButton
import com.thanasis.e_thessbike.ui.components.LogoutButton
import com.thanasis.e_thessbike.ui.components.ProfileCard
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileInit(navController: NavHostController, value: String, userLoggedIn: Array<String>) {
    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        },
        floatingActionButton = {
            Row {
                LogoutButton(navController)
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 77.dp)
        ) {
            Scaffold(
                modifier = Modifier.height(250.dp),
                floatingActionButton = { EditButton(navController) }
            ) {
                ProfileCard(userLoggedIn)
            }
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider()
            Scaffold(
                modifier = Modifier.padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(85.dp, 50.dp)
                ) {
                    ButtonComp(stringResource(id = R.string.bike_list), navController)
                    Spacer(modifier = Modifier.padding(0.dp, 10.dp))
                    ButtonComp(stringResource(id = R.string.available_bikes), navController)
                    /*Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ButtonComp(stringResource(id = R.string.bike_list), navController)
                        Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                        ButtonComp(stringResource(id = R.string.favourites), navController)
                    }
                    Spacer(modifier = Modifier.padding(0.dp, 5.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ButtonComp(stringResource(id = R.string.available_bikes), navController)
                    }*/
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileInitPreview() {
    val navController = rememberNavController()
    ProfileInit(navController, "Profile", userLoggedIn = arrayOf("", ""))
}
