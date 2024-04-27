package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.logout.logout

@Composable
fun EditButton(navHostController: NavHostController) {
    FloatingActionButton(
        onClick = { navHostController.navigate(EThessBikeApp.EditInfo.name) },
        content = { Icon(Icons.Filled.Edit, "Floating action button.") },
    )
}

@Composable
fun LogoutButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            logout(navController)
        },
        content = { Icon(Icons.AutoMirrored.Filled.Logout, "Floating action button.") }
    )
}

@Composable
fun ApplyButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(EThessBikeApp.Profile.name)
        },
        content = { Icon(Icons.Filled.Check, "Floating action button.") }
    )
}

@Composable
fun AddButton() {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        content = { ButtonContent(Icons.Filled.Add, stringResource(id = R.string.add_bike), 15) },
    )
}

@Composable
fun RemoveButton() {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        content = { ButtonContent(Icons.Filled.Remove, stringResource(id = R.string.remove_bike), 30) },
    )
}

@Composable
fun ButtonContent(icon: ImageVector, value: String, horizontal: Int) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(icon,
            "Floating action button.",
            modifier = Modifier.padding(horizontal.dp, 0.dp))
        Text(text = value,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ButtonPreview() {
    val navController = rememberNavController()
    ApplyButton(navController)
}