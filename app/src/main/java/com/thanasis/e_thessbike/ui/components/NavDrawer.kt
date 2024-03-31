package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.ui.screens.HomeInit
import com.thanasis.e_thessbike.ui.screens.ProfileInit
import com.thanasis.e_thessbike.ui.screens.SettingsInit

@Composable
fun MenuTitle() {
    Text("Main menu",
        fontSize = 34.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun HomeItem(navHostController: NavHostController) {
    NavigationDrawerItem(
        label = { Text(text = "Home") },
        selected = false,
        onClick = { navHostController.navigate(EThessBikeApp.Home.name) },
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
    )
}

@Composable
fun SettingsItem(navHostController: NavHostController) {
    NavigationDrawerItem(
        label = { Text(text = "Settings") },
        selected = false,
        onClick = { navHostController.navigate(EThessBikeApp.Settings.name) },
        icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
    )
}

@Composable
fun MenuDrawer(navController: NavHostController, selectedIndex: String) {
    // [START android_compose_layout_material_modal_drawer]
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                MenuTitle()
                Divider()
                HomeItem(navController)
                SettingsItem(navController)
            }
        }
    ) {
        when (selectedIndex) {
            "home" -> {
                HomeInit(navController, "e-ThessBike")
            }
            "settings" -> {
                SettingsInit(navController, "Settings")
            }
            "profile" -> {
                ProfileInit(navController, "Profile")
            }
        }
    }
}
