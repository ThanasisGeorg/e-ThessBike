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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.editInfo.EditInfoUIEvent
import com.thanasis.e_thessbike.backend.editInfo.EditInfoUIViewModel
import com.thanasis.e_thessbike.backend.initInfo
import com.thanasis.e_thessbike.ui.components.ApplyButton
import com.thanasis.e_thessbike.ui.components.BackButton
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun EditInfoInit(navController: NavHostController, value: String, db: FirebaseFirestore, userLoggedIn: Array<String>) {
    val editInfoUIViewModel = EditInfoUIViewModel(userLoggedIn)
    val context = LocalContext.current
    val data = initInfo("users_info")

    Scaffold(
        floatingActionButton = {
            Row {
                ApplyButton(navController, editInfoUIViewModel, db, context, userLoggedIn)
                Spacer(modifier = Modifier.padding(5.dp, 0.dp))
                BackButton(navController)
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
            data.documents[userLoggedIn[0].toInt()].getString("name")?.let { it ->
                TextField(
                    labelValue = stringResource(id = R.string.first_name),
                    textValue = "$it ",
                    onTextSelected = {
                        editInfoUIViewModel.onEditEvent(EditInfoUIEvent.FirstNameChanged(it), navController, db, userLoggedIn)
                    },
                    //errorStatus = editInfoUIViewModel.signUpUIState.value.firstNameError
                    //painterResource(id = R.drawable.rounded_account_circle_24)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            data.documents[userLoggedIn[0].toInt()].getString("surname")?.let { it ->
                TextField(
                    labelValue = stringResource(id = R.string.last_name),
                    textValue = "$it ",
                    onTextSelected = {
                        editInfoUIViewModel.onEditEvent(EditInfoUIEvent.LastNameChanged(it), navController, db, userLoggedIn)
                    },
                    //errorStatus = editInfoUIViewModel.signUpUIState.value.lastNameError
                    //painterResource(id = R.drawable.rounded_account_circle_24)
                )
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditInfoInit() {
    Scaffold(
        floatingActionButton = {
            Row {
                //ApplyButton(navController)
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
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.firstNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.last_name),
                textValue = stringResource(id = R.string.last_name),
                onTextSelected = {

                },
                errorStatus = true
                //errorStatus = signUpViewModel.signUpUIState.value.lastNameError
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
        }
    }
}

@Preview
@Composable
fun EditInfoPreview() {
    EditInfoInit()
}