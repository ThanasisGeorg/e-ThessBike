package com.thanasis.e_thessbike.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpUIEvent
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import com.thanasis.e_thessbike.ui.components.CheckBox
import com.thanasis.e_thessbike.ui.components.ClickableLoginTextComp
import com.thanasis.e_thessbike.ui.components.DividerComp
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.NormalText
import com.thanasis.e_thessbike.ui.components.PasswordTextField
import com.thanasis.e_thessbike.ui.components.TextField
import com.thanasis.e_thessbike.ui.components.loginRegisterButtonComp

@Composable
fun registerScreen(navController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, signUpViewModel: SignUpViewModel = viewModel()): Array<String> {
    var userLoggedIn by remember { mutableStateOf(arrayOf("", "")) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(value = stringResource(id = R.string.hello), TextAlign.Center, 24)
            HeadingText(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                labelValue = stringResource(id = R.string.first_name),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it), navController, db, roomDb, context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.firstNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            TextField(
                labelValue = stringResource(id = R.string.last_name),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it), navController, db, roomDb, context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.lastNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            TextField(
                labelValue = stringResource(id = R.string.email),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it), navController, db, roomDb, context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.emailError
                //painterResource(id = android.R.drawable.ic_dialog_email)
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it), navController, db, roomDb, context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.passwordError
                //painterResource(id = android.R.drawable.ic_lock_idle_lock)
            )
            CheckBox(
                onCheckedChange = {
                    signUpViewModel.onEvent(SignUpUIEvent.ConditionsAndPrivacyClicked(it), navController, db, roomDb, context)
                },
                navController
            )

            Spacer(modifier = Modifier.height(80.dp))

             userLoggedIn = loginRegisterButtonComp(
                value = stringResource(id = R.string.register),
                navController,
                db,
                roomDb,
                context,
                signUpViewModel,
                isEnabled = signUpViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))
            DividerComp()
            Spacer(modifier = Modifier.height(20.dp))

            ClickableLoginTextComp(
                loginAttempt = true, onTextSelected = {
                    navController.navigate(EThessBikeApp.Login.name)
                }
            )
        }
    }

    return userLoggedIn
}

@Preview
@Composable
fun RegisterScreenPreview(){
    //RegisterScreen(navController)
}