package com.thanasis.e_thessbike.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.login.LoginUIEvent
import com.thanasis.e_thessbike.backend.login.LoginViewModel
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpUIEvent
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import com.thanasis.e_thessbike.ui.theme.Purple40
import com.thanasis.e_thessbike.ui.theme.Purple80

@Composable
fun loginRegisterButtonComp(value: String, navController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, context: Context, viewModel: ViewModel, isEnabled: Boolean = false): Array<String> {
    var userLoggedIn by remember { mutableStateOf(arrayOf("", "")) }

    Button(
        onClick = {
            if (viewModel is LoginViewModel) {
                userLoggedIn = viewModel.onEvent(LoginUIEvent.LoginBtnClicked, navController, db, roomDb, context)
            } else if (viewModel is SignUpViewModel) {
                userLoggedIn = viewModel.onEvent(SignUpUIEvent.RegisterBtnClicked, navController, db, roomDb, context)
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Purple40, Purple80)),
                shape = RoundedCornerShape(50.dp)
            ),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
                contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    return userLoggedIn
}

@Composable
fun ButtonComp(value: String, navHostController: NavHostController) {
    Button(
        onClick = {
            if (value == "My bike list") navHostController.navigate(EThessBikeApp.MyBikeList.name)
            else navHostController.navigate(EThessBikeApp.AllBikeList.name)
        },
        modifier = Modifier
            .size(160.dp, 100.dp)
            .heightIn(48.dp)
            .background(
                color = Purple40,
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .heightIn(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                modifier = Modifier.padding(10.dp, 0.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ButtonCompPreview() {
    ButtonComp(value = "", navHostController = rememberNavController())
}

