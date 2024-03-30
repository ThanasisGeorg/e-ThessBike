package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp, 77.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 383.dp, height = 300.dp)
        ) {
            /*Text(
                text = "Profile",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )*/
        }
    }

}
