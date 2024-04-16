package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditInfoInit(navController: NavHostController, value: String, db: FirebaseFirestore) {
    Scaffold {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = stringResource(id = R.string.edit_info))
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                labelValue = stringResource(id = R.string.first_name),
                onTextSelected = {

                },
                //errorStatus = signUpViewModel.signUpUIState.value.firstNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.last_name),
                onTextSelected = {

                },
                //errorStatus = signUpViewModel.signUpUIState.value.lastNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {

                },
                //errorStatus = signUpViewModel.signUpUIState.value.emailError
                //painterResource(id = android.R.drawable.ic_dialog_email)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onTextSelected = {

                },
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
        }
    }
}

@Preview
@Composable
fun EditInfoPreview() {
    val navController = rememberNavController()
    //EditInfoInit(navController, value = "Edit your information")
}