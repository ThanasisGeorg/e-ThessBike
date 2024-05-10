package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.changePw.ChangePasswordUIEvent
import com.thanasis.e_thessbike.backend.changePw.ChangePasswordUIViewModel
import com.thanasis.e_thessbike.ui.components.ApplyButton
import com.thanasis.e_thessbike.ui.components.BackButton
import com.thanasis.e_thessbike.ui.components.ErrorListDropdown
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.PasswordTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(navController: NavHostController, value: String, db: FirebaseFirestore, userLoggedIn: Array<String>, changePasswordUIViewModel: ChangePasswordUIViewModel = viewModel()){
    val context = LocalContext.current
    var isEnabled by remember { mutableStateOf(false) }
    var errorIndex by remember { mutableIntStateOf(0) }
    val errorList = mutableListOf<String>()
    val newPwError = "Enter a different password from the old one\n" + "and make sure is greater or equal to 8 characters"
    val confirmNewPwError = "This password does not match with your new one"

    errorList.add(newPwError)
    errorList.add(confirmNewPwError)

    Scaffold (
        floatingActionButton = {
            Row {
                ApplyButton(
                    navController = navController,
                    changePasswordUIViewModel = changePasswordUIViewModel,
                    db = db,
                    userLoggedIn = userLoggedIn,
                    errorList,
                    context = context
                )
                Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                BackButton(navController, "settings")
            }
        }
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = value)
            Spacer(modifier = Modifier.height(40.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.new_password),
                onTextSelected = {
                    errorIndex = changePasswordUIViewModel.onEvent(ChangePasswordUIEvent.NewPasswordChanged(it), navController, db, userLoggedIn, context)
                    isEnabled = errorIndex == 1
                }
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.confirm_new_password),
                onTextSelected = {
                    errorIndex = changePasswordUIViewModel.onEvent(ChangePasswordUIEvent.ConfirmNewPasswordChanged(it), navController, db, userLoggedIn, context)
                },
                isEnabled = isEnabled
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            when (errorIndex) {
                -1 -> {
                    ErrorListDropdown(errorList, 0)
                }
                -2 -> {
                    ErrorListDropdown(errorList, 1)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(value: String){
    val errorList = listOf<String>()

    Scaffold (
        floatingActionButton = {
            //ApplyButton()
        }
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = value)
            Spacer(modifier = Modifier.height(40.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.new_password),
                onTextSelected = {

                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.confirm_new_password),
                onTextSelected = {

                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box {
                Popup {
                    Column {
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(text = "")
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(value = "Change my password")
}