package com.thanasis.e_thessbike.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.login.LoginUIEvent
import com.thanasis.e_thessbike.backend.login.LoginViewModel
import com.thanasis.e_thessbike.ui.components.ButtonComp
import com.thanasis.e_thessbike.ui.components.ClickableLoginTextComp
import com.thanasis.e_thessbike.ui.components.DividerComp
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.NormalText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField
import com.thanasis.e_thessbike.ui.components.UnderLinedText

@Composable
fun LoginScreen(navController: NavHostController, db: FirebaseFirestore, loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NormalText(value = stringResource(id = R.string.hello))
            HeadingText(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.heightIn(20.dp))

            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it), navController, db)
                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it), navController, db)
                },
                //errorStatus = loginViewModel.loginUIState.value.passwordError
            )

            Spacer(modifier = Modifier.heightIn(20.dp))

            UnderLinedText(value = stringResource(id = R.string.forgot_password))

            Spacer(modifier = Modifier.heightIn(20.dp))

            ButtonComp(
                value = stringResource(id = R.string.login),
                navHostController = navController,
                onBtnClicked = {
                    loginViewModel.onEvent(LoginUIEvent.LoginBtnClicked, navController, db)
                },
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(20.dp))
            DividerComp()
            Spacer(modifier = Modifier.height(20.dp))

            ClickableLoginTextComp(
                loginAttempt = false,
                onTextSelected = {
                    navController.navigate(EThessBikeApp.Register.name)
                }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    //LoginScreen(navController)
}