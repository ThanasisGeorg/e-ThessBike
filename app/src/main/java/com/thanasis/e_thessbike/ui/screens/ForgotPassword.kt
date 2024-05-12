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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.forgotPw.ForgotPasswordUIEvent
import com.thanasis.e_thessbike.backend.forgotPw.ForgotPasswordUIViewModel
import com.thanasis.e_thessbike.ui.components.ApplyButton
import com.thanasis.e_thessbike.ui.components.BackButton
import com.thanasis.e_thessbike.ui.components.ErrorListDropdown
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen(navController: NavHostController, value: String, db: FirebaseFirestore, forgotPasswordUIViewModel: ForgotPasswordUIViewModel = viewModel()){
    val context = LocalContext.current
    var isNewPwEnabled by remember { mutableStateOf(false) }
    var isConfirmNewPwEnabled by remember { mutableStateOf(false) }
    var errorIndex by remember { mutableIntStateOf(0) }
    val errorList = mutableListOf<String>()
    val emailError = "This email does not exist"
    val newPwError = "Enter a different password from the old one\n" + "and make sure is greater or equal to 8 characters"
    val confirmNewPwError = "This password does not match with your new one"

    errorList.add(emailError)
    errorList.add(newPwError)
    errorList.add(confirmNewPwError)

    Scaffold (
        floatingActionButton = {
            Row {
                ApplyButton(
                    navController = navController,
                    forgotPasswordUIViewModel = forgotPasswordUIViewModel,
                    db = db,
                    context = context
                )
                Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                BackButton(navController, "login")
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
            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {
                    errorIndex = forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.EmailChanged(it), navController, db, context)
                    isNewPwEnabled = errorIndex == 1
                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.new_password),
                onTextSelected = {
                    errorIndex = forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.NewPasswordChanged(it), navController, db, context)
                    isConfirmNewPwEnabled = errorIndex == 2
                },
                isEnabled = isNewPwEnabled
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.confirm_new_password),
                onTextSelected = {
                    errorIndex = forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.ConfirmNewPasswordChanged(it), navController, db, context)
                },
                isEnabled = isConfirmNewPwEnabled
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
                -3 -> {
                    ErrorListDropdown(errorList, 2)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen(value: String){
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
            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {

                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(15.dp))
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
        }
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(value = "Forgot my password")
}