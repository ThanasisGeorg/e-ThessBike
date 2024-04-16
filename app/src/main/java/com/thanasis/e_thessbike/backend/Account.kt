package com.thanasis.e_thessbike.backend

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.thanasis.e_thessbike.backend.signUp.SignUpUIState

class Account {
    private var firstName = ""
    private var lastName = ""
    private var email = ""
    val db = Firebase.firestore

    private val signUpUIState = mutableStateOf(SignUpUIState())

    fun setFirstName(fName: String) {
        firstName = fName
    }

    fun setLastName(lName: String) {
        lastName = lName
    }

    fun setEmail(email: String) {
        this.email = email
    }

    @Composable
    fun FirstName() {

        Text(
            text = signUpUIState.value.firstName,
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center,
        )
    }

    @Composable
    fun LastName() {
        Text(
            text = signUpUIState.value.lastName,
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center,
        )
    }

    @Composable
    fun Email() {
        Text(
            text = signUpUIState.value.email,
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            textAlign = TextAlign.Center,
        )
    }

}
