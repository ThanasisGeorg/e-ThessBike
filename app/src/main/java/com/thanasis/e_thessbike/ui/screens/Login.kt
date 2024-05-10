package com.thanasis.e_thessbike.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.login.LoginUIEvent
import com.thanasis.e_thessbike.backend.login.LoginViewModel
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.ui.components.ClickableLoginTextComp
import com.thanasis.e_thessbike.ui.components.DividerComp
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.NormalText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField
import com.thanasis.e_thessbike.ui.components.loginRegisterButtonComp
import com.thanasis.e_thessbike.ui.theme.Purple40

@Composable
fun loginScreen(navController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, onClick: () -> Unit, loginViewModel: LoginViewModel = viewModel()): Array<String> {
    var userLoggedIn by remember{ mutableStateOf(arrayOf("", "")) }
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(stringResource(id = R.string.forgot_password))
        withStyle(style = SpanStyle(
            color = Purple40,
            textDecoration = TextDecoration.Underline)
        ) {}
    }

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
            NormalText(value = stringResource(id = R.string.hello), TextAlign.Center, 24)
            HeadingText(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.heightIn(20.dp))

            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it), navController, db, roomDb, context, onClick)
                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it), navController, db, roomDb, context, onClick)
                },
                //errorStatus = loginViewModel.loginUIState.value.passwordError
            )

            Spacer(modifier = Modifier.heightIn(20.dp))

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp),
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center
                ),
                text = annotatedString,
                onClick = {
                    navController.navigate(EThessBikeApp.ForgotPassword.name)
                }
            )

            Spacer(modifier = Modifier.heightIn(20.dp))

            userLoggedIn = loginRegisterButtonComp(
                value = stringResource(id = R.string.login),
                navController,
                db,
                roomDb,
                context,
                loginViewModel,
                onClick,
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

    return userLoggedIn
}