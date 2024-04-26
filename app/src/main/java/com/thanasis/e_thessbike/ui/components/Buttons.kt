package com.thanasis.e_thessbike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.backend.login.LoginUIEvent
import com.thanasis.e_thessbike.backend.login.LoginViewModel
import com.thanasis.e_thessbike.backend.roomAPI.AppDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpUIEvent
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel
import com.thanasis.e_thessbike.ui.theme.Purple40
import com.thanasis.e_thessbike.ui.theme.Purple80

@Composable
fun buttonComp(value: String, navController: NavHostController, db: FirebaseFirestore, roomDb: AppDatabase, viewModel: ViewModel, isEnabled: Boolean = false): Array<String> {
    var userLoggedIn by remember { mutableStateOf(arrayOf("", "")) }
    val TAG: String? = SignUpViewModel::class.simpleName

    Button(
        onClick = {
            if (viewModel is LoginViewModel) {
                userLoggedIn = viewModel.onEvent(LoginUIEvent.LoginBtnClicked, navController, db, roomDb)
            } else if (viewModel is SignUpViewModel) {
                userLoggedIn = viewModel.onEvent(SignUpUIEvent.RegisterBtnClicked, navController, db, roomDb)
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
