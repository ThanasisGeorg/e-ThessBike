package com.thanasis.e_thessbike.backend.addBike

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

class AddBikeUIViewModel: ViewModel() {
    var addBikeUIState = mutableStateOf(AddBikeUIState())

    fun onAddEvent(event: AddBikeUIEvent, navHostController: NavHostController, db: FirebaseFirestore, context: Context, userLoggedIn: Array<String>) {
        when (event) {
            is AddBikeUIEvent.BrandNameChanged -> {
                addBikeUIState.value = addBikeUIState.value.copy(
                    brandName = event.brandName
                )
            }
            is AddBikeUIEvent.ColorChanged -> {
                addBikeUIState.value = addBikeUIState.value.copy(
                    color = event.color
                )
            }
            is AddBikeUIEvent.AddBtnClicked -> {

            }
        }
    }
}