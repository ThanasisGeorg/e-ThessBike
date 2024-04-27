package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.thanasis.e_thessbike.backend.initInfo
import com.thanasis.e_thessbike.ui.components.ApplyButton
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun EditInfoInit(navController: NavHostController, value: String, db: FirebaseFirestore, userLoggedIn: Array<String>) {
    val nameData = initInfo("users_info", userLoggedIn[0].toInt(), "name")
    val surnameData = initInfo("users_info", userLoggedIn[0].toInt(), "surname")
    val emailData = initInfo("users_info", userLoggedIn[0].toInt(), "email")

    Scaffold(
        floatingActionButton = {
            Row {
                ApplyButton(navController)
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = value)
            Spacer(modifier = Modifier.height(40.dp))
            if (nameData != null) {
                TextField(
                    labelValue = stringResource(id = R.string.first_name),
                    textValue = nameData.documents[userLoggedIn[0].toInt()].getString("name").toString(),
                    onTextSelected = {
                        /*TODO*/
                    },
                    errorStatus = true,
                    db = db
                    //painterResource(id = R.drawable.rounded_account_circle_24)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            if (surnameData != null) {
                TextField(
                    labelValue = stringResource(id = R.string.last_name),
                    textValue = surnameData.documents[userLoggedIn[0].toInt()].getString("surname").toString(),
                    onTextSelected = {
                        /*TODO*/
                    },
                    errorStatus = true,
                    db = db
                    //painterResource(id = R.drawable.rounded_account_circle_24)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            if (emailData != null) {
                TextField(
                    labelValue = stringResource(id = R.string.email),
                    textValue = emailData.documents[userLoggedIn[0].toInt()].getString("email").toString(),
                    onTextSelected = {
                        /*TODO*/
                    },
                    errorStatus = true,
                    db = db
                    //painterResource(id = android.R.drawable.ic_dialog_email)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            /*PasswordTextField(
                labelValue = "",
                onTextSelected = {

                },
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )*/
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditInfoInit(navController: NavHostController, value: String) {
    Scaffold(
        floatingActionButton = {
            Row {
                ApplyButton(navController)
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = stringResource(id = R.string.edit_info))
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                labelValue = stringResource(id = R.string.first_name),
                textValue = stringResource(id = R.string.first_name),
                onTextSelected = {

                },
                errorStatus = true,
                db = FirebaseFirestore.getInstance()
                //errorStatus = signUpViewModel.signUpUIState.value.firstNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.last_name),
                textValue = stringResource(id = R.string.last_name),
                onTextSelected = {

                },
                errorStatus = true,
                db = FirebaseFirestore.getInstance()
                //errorStatus = signUpViewModel.signUpUIState.value.lastNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.email),
                textValue = stringResource(id = R.string.email),
                onTextSelected = {

                },
                errorStatus = true,
                db = FirebaseFirestore.getInstance()
                //errorStatus = signUpViewModel.signUpUIState.value.emailError
                //painterResource(id = android.R.drawable.ic_dialog_email)
            )
            Spacer(modifier = Modifier.height(15.dp))
            /*PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onTextSelected = {

                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )*/
        }
    }
}

@Preview
@Composable
fun EditInfoPreview() {
    val navController = rememberNavController()
    EditInfoInit(navController, value = "Edit your information")
}