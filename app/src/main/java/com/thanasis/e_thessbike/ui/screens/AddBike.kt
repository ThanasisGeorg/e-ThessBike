package com.thanasis.e_thessbike.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.NotificationService
import com.thanasis.e_thessbike.R
import com.thanasis.e_thessbike.backend.addBike.AddBikeUIEvent
import com.thanasis.e_thessbike.backend.addBike.AddBikeUIViewModel
import com.thanasis.e_thessbike.ui.components.ApplyButton
import com.thanasis.e_thessbike.ui.components.DropdownList
import com.thanasis.e_thessbike.ui.components.HeadingText
import com.thanasis.e_thessbike.ui.components.TextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddBikeInit(navHostController: NavHostController, value: String, db: FirebaseFirestore, userLoggedIn: Array<String>, notificationService: NotificationService, addBikeUIViewModel: AddBikeUIViewModel = viewModel()){
    val itemList = listOf<String>("Sikies", "Neapoli", "Stavroupoli", "Evosmos", "Polichni", "Thessaloiniki", "Kalamaria", "Meteora")
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            ApplyButton(navHostController, addBikeUIViewModel, db, context, userLoggedIn, notificationService)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp, 30.dp)
        ) {
            HeadingText(value = stringResource(id = R.string.add_bike))
            TextField(
                labelValue = stringResource(id = R.string.brand_name),
                onTextSelected = {
                    addBikeUIViewModel.onAddEvent(
                        AddBikeUIEvent.BrandNameChanged(it),
                        navHostController,
                        db,
                        context,
                        userLoggedIn,
                        notificationService
                    )
                },
                errorStatus = true
                //painterResource(id = R.drawable.rounded_account_circle_24)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                labelValue = stringResource(id = R.string.color),
                onTextSelected = {
                    addBikeUIViewModel.onAddEvent(
                        AddBikeUIEvent.ColorChanged(it),
                        navHostController,
                        db,
                        context,
                        userLoggedIn,
                        notificationService
                    )
                },
                errorStatus = true
            )
            Spacer(modifier = Modifier.height(15.dp))
            DropdownList(
                itemList,
                selectedIndex,
                onItemClick = {
                    selectedIndex = it
                    addBikeUIViewModel.onAddEvent(
                        AddBikeUIEvent.LocationChanged(itemList[it]),
                        navHostController,
                        db,
                        context,
                        userLoggedIn,
                        notificationService
                    )
                }
            )
        }
    }
}