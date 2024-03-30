package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thanasis.e_thessbike.ui.theme.Purple80

@Composable
fun DividerComp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )

            Text(text = " or ", fontSize = 14.sp, color = Purple80)

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.Gray,
                thickness = 1.dp
            )
    }
}