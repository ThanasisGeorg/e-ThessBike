package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.components.ButtonComp_
import com.thanasis.e_thessbike.ui.components.CategoryIndicator
import com.thanasis.e_thessbike.ui.components.ThemeSwitch
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsInit(navController: NavHostController, value: String, roomDb: AppDatabase, darkTheme: Boolean, onThemeUpdated: () -> Unit){
    Scaffold(
        topBar = {
            TopBar(navController, title = value)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(18.dp, 77.dp)
        ) {
            Spacer(modifier = Modifier.padding(5.dp))
            CategoryIndicator(category = "theme", textSize = 25)
            HorizontalDivider()
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryIndicator(category = "dark", textSize = 15)
                Spacer(modifier = Modifier.padding(5.dp, 10.dp))
                ThemeSwitch(
                    darkTheme = darkTheme,
                    size = 40.dp,
                    padding = 5.dp,
                    onClick = onThemeUpdated,
                    roomDb = roomDb
                )
                Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                CategoryIndicator(category = "light", textSize = 15)
            }

            Spacer(modifier = Modifier.padding(10.dp))

            CategoryIndicator(category = "security", textSize = 25)
            HorizontalDivider()
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            ButtonComp_("Change password", navController)
        }
    }
}