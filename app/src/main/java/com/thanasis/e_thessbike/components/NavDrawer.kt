package com.thanasis.e_thessbike.components

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


class NavDrawer {
    private val topBar = TopBar()

    @Composable
    fun MenuTitle() {
        Text("Main menu",
            fontSize = 34.sp,
            modifier = Modifier.padding(16.dp)
        )
    }

    @Composable
    fun HomeItem() {
        NavigationDrawerItem(
            label = { Text(text = "Home") },
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
        )
    }

    @Composable
    fun SettingsItem() {
        NavigationDrawerItem(
            label = { Text(text = "Settings") },
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
        )
    }

    @Composable
    fun MenuDrawer() {
        // [START android_compose_layout_material_modal_drawer]
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    MenuTitle()
                    Divider()
                    HomeItem()
                    SettingsItem()
                }
            }
        ) {
            topBar.TopAppBar_()
        }
    }
}

