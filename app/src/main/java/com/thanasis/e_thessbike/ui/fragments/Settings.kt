package com.thanasis.e_thessbike.ui.fragments

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SettingsInfo(){
    Text("Settings", textAlign = TextAlign.Center, modifier = Modifier.width(150.dp))
}