package com.thanasis.e_thessbike.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.ui.components.ButtonComp
import com.thanasis.e_thessbike.ui.components.CheckBox
import com.thanasis.e_thessbike.ui.components.ClickableLoginTextComp
import com.thanasis.e_thessbike.ui.components.DividerComp
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.NormalText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField

@Composable
fun RegisterScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(value = stringResource(id = R.string.hello))
            HeadingText(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                labelValue = stringResource(id = R.string.first_name),
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            TextField(labelValue = stringResource(id = R.string.last_name),
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            TextField(labelValue = stringResource(id = R.string.email),
                //painterResource(id = android.R.drawable.ic_dialog_email)
            )
            PasswordTextField(labelValue = stringResource(id = R.string.password),
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            CheckBox(value = stringResource(id = R.string.terms_of_use))
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComp(value = stringResource(id = R.string.register), navController)
            Spacer(modifier = Modifier.height(20.dp))
            DividerComp()
            Spacer(modifier = Modifier.height(20.dp))
            ClickableLoginTextComp(onTextSelected = {})
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    //RegisterScreen(navController)
}