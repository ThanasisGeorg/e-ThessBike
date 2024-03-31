package com.thanasis.e_thessbike.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditButton() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
    ) {
        Icon(Icons.Filled.Edit, "Floating action button.")
    }
}

@Preview
@Composable
fun EditButtonPreview() {
    EditButton()
}