package com.thanasis.e_thessbike.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.NotificationService
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.addBike.AddBikeUIEvent
import com.thanasis.e_thessbike.backend.addBike.AddBikeUIViewModel
import com.thanasis.e_thessbike.backend.forgotPw.ForgotPasswordUIEvent
import com.thanasis.e_thessbike.backend.forgotPw.ForgotPasswordUIViewModel
import com.thanasis.e_thessbike.backend.logout.logout
import com.thanasis.e_thessbike.backend.onEditEvent
import com.thanasis.e_thessbike.backend.removeBike
import com.thanasis.e_thessbike.backend.signUp.SignUpUIEvent
import com.thanasis.e_thessbike.backend.signUp.SignUpUIState
import com.thanasis.e_thessbike.ui.theme.Purple40

@Composable
fun EditButton(navHostController: NavHostController) {
    FloatingActionButton(
        onClick = { navHostController.navigate(EThessBikeApp.EditInfo.name) },
        content = { Icon(Icons.Filled.Edit, "Floating action button.") },
    )
}

@Composable
fun LogoutButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            logout(navController)
        },
        content = { Icon(Icons.AutoMirrored.Filled.Logout, "Floating action button.") }
    )
}

@Composable
fun ApplyButton(navController: NavHostController, signUpUIState: MutableState<SignUpUIState>, db: FirebaseFirestore, context: Context, userLoggedIn: Array<String>) {
    FloatingActionButton(
        onClick = {
            onEditEvent(SignUpUIEvent.ApplyBtnClicked, signUpUIState, navController, db, context, userLoggedIn)
        },
        content = { Icon(Icons.Filled.Check, "Floating action button.") }
    )
}

@Composable
fun ApplyButton(navController: NavHostController, addBikeUIViewModel: AddBikeUIViewModel, db: FirebaseFirestore, context: Context, userLoggedIn: Array<String>, notificationService: NotificationService) {
    FloatingActionButton(
        onClick = {
            addBikeUIViewModel.onAddEvent(AddBikeUIEvent.AddBtnClicked, navController, db, context, userLoggedIn, notificationService)
        },
        content = { Icon(Icons.Filled.Check, "Floating action button.") }
    )
}

@Composable
fun ApplyButton(navController: NavHostController, forgotPasswordUIViewModel: ForgotPasswordUIViewModel, db: FirebaseFirestore, context: Context) {
    FloatingActionButton(
        onClick = {
            forgotPasswordUIViewModel.onEvent(ForgotPasswordUIEvent.ApplyBtnClicked, navController, db, context)
        },
        content = { Icon(Icons.Filled.Check, "Floating action button.") }
    )
}

@Composable
fun ApplyButton() {
    FloatingActionButton(
        onClick = {},
        content = { Icon(Icons.Filled.Check, "Floating action button.") }
    )
}

@Composable
fun AddButton(navHostController: NavHostController) {
    ExtendedFloatingActionButton(
        onClick = { navHostController.navigate(EThessBikeApp.AddBike.name) },
        content = { ButtonContent(Icons.Filled.Add, stringResource(id = R.string.add_bike), 15) },
    )
}

@Composable
fun RemoveButton(userLoggedIn: Array<String>, index: Int, navHostController: NavHostController, notificationService: NotificationService) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    ExtendedFloatingActionButton(
        onClick = { openAlertDialog.value = !openAlertDialog.value},
        content = { ButtonContent(Icons.Filled.Remove, stringResource(id = R.string.remove_bike), 30) },
    )
    
    if (openAlertDialog.value) {
        RemoveBikeAlertDialog(
            onDismissRequest = { openAlertDialog.value = false},
            onConfirmation = {
                openAlertDialog.value = false
                removeBike(userLoggedIn, index, context)
                navHostController.navigate(EThessBikeApp.MyBikeList.name)
                notificationService.showBasicNotification("Successful remove", "You have successfully removed your bike")
                //Toast.makeText(context, "Bike removed successfully", Toast.LENGTH_LONG).show()
            },
            dialogTitle = "Remove your bike",
            dialogText = "Are you sure you want to remove this bike?",
            icon = Icons.Default.Warning
        )
    }
}

@Composable
fun FavouriteButton() {
    var isFavourite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavourite,
        onCheckedChange = {
            isFavourite = !isFavourite
        }
    ) {
        Icon(
            tint = Purple40,
            modifier = Modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector =
                if (isFavourite) Icons.Filled.Favorite
                else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }
}

@Composable
fun ButtonContent(icon: ImageVector, value: String, horizontal: Int) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(icon,
            "Floating action button.",
            modifier = Modifier.padding(horizontal.dp, 0.dp))
        Text(text = value,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ButtonPreview() {
    //val navController = rememberNavController()
    //ApplyButton(navController)
}