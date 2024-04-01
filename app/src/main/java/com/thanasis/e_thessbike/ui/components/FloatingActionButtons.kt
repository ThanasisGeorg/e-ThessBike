package com.thanasis.e_thessbike.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp

@Composable
fun EditButton() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
    ) {
        Icon(Icons.Filled.Edit, "Floating action button.")
    }
}

@Composable
fun LogoutButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(EThessBikeApp.Login.name) },
    ) {
        Icon(Icons.AutoMirrored.Filled.Logout, "Floating action button.")
    }
}

@Composable
fun AddButton() {
    ExtendedFloatingActionButton(
        onClick = { },
        icon = { Icon(Icons.Filled.Add, "Floating action button.") },
        text = { Text(text = "Add bike") }
    )
}

@Composable
fun RemoveButton() {
    ExtendedFloatingActionButton(
        onClick = { },
        icon = { Icon(Icons.Filled.Remove, "Floating action button.") },
        text = { Text(text = "Remove bike") }
    )
}

@Preview
@Composable
fun EditButtonPreview() {
    EditButton()
}