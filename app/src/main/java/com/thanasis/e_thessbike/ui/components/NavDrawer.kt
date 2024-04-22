package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.screens.EditInfoInit
import com.thanasis.e_thessbike.ui.screens.HomeInit
import com.thanasis.e_thessbike.ui.screens.ProfileInit
import com.thanasis.e_thessbike.ui.screens.SettingsInit

@Composable
fun MenuTitle() {
    Text(
        stringResource(id = R.string.main_menu),
        fontSize = 34.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun HomeItem(navHostController: NavHostController) {
    NavigationDrawerItem(
        label = { Text(text = stringResource(id = R.string.home)) },
        selected = false,
        onClick = { navHostController.navigate(EThessBikeApp.Home.name) },
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
    )
}

@Composable
fun SettingsItem(navHostController: NavHostController) {
    NavigationDrawerItem(
        label = { Text(text = stringResource(id = R.string.settings)) },
        selected = false,
        onClick = { navHostController.navigate(EThessBikeApp.Settings.name) },
        icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
    )
}

@Composable
fun MenuDrawer(navController: NavHostController, selectedIndex: String, db: FirebaseFirestore, roomDb: AppDatabase, darkTheme: Boolean, onThemeUpdated: () -> Unit) {
    // [START android_compose_layout_material_modal_drawer]
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                MenuTitle()
                HorizontalDivider(thickness = 2.dp)
                HomeItem(navController)
                SettingsItem(navController)
            }
        }
    ) {
        when (selectedIndex) {
            "home" -> {
                HomeInit(navController, stringResource(id = R.string.app_name))
            }
            "settings" -> {
                SettingsInit(navController, stringResource(id = R.string.settings), roomDb, darkTheme, onThemeUpdated)
            }
            "profile" -> {
                ProfileInit(navController, stringResource(id = R.string.profile))
            }
            "editInfo" -> {
                EditInfoInit(navController, stringResource(id = R.string.edit_info), db)
            }
        }
    }
}
