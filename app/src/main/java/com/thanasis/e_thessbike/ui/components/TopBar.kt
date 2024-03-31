package com.thanasis.e_thessbike.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.EThessBikeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, title: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = { navController.navigate(EThessBikeApp.Profile.name) }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "")
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 30.sp
            )
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    val navController = rememberNavController()
    TopBar(navController = navController, title = "")
}
