package com.thanasis.e_thessbike.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.ui.components.HeadingTextComponent
import com.thanasis.e_thessbike.ui.components.NormalTextComponent
import com.thanasis.e_thessbike.ui.components.TextField

@Composable
fun RegisterScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))

            TextField(labelValue = stringResource(id = R.string.first_name))
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    RegisterScreen()
}