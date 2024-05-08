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
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen(navController: NavHostController, value: String, db: FirebaseFirestore, forgotPasswordUIViewModel: ForgotPasswordUIViewModel = viewModel()){
    val context = LocalContext.current

    Scaffold (
        floatingActionButton = {
            ApplyButton(
                navController = navController,
                forgotPasswordUIViewModel = forgotPasswordUIViewModel,
                db = db,
                context = context
            )
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
                    forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.EmailChanged(it), navController, db, context)
                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.new_password),
                onTextSelected = {
                    forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.NewPasswordChanged(it), navController, db, context)
                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            Spacer(modifier = Modifier.height(15.dp))
            PasswordTextField(
                labelValue = stringResource(id = R.string.confirm_new_password),
                onTextSelected = {
                    forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.ConfirmNewPasswordChanged(it), navController, db, context)
                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
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