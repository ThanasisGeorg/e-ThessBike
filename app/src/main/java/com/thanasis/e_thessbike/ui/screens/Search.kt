package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.ui.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchInit(navHostController: NavHostController, value: String) {
    Scaffold(
        topBar = {
            TopBar(navHostController, title = value)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(18.dp, 77.dp)
        ) {
            Text(text = "Home Information")
        }
    }
}