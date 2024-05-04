package com.thanasis.e_thessbike.backend.addBike

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.thanasis.e_thessbike.EThessBikeApp
import com.thanasis.e_thessbike.backend.rules.Validator
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class AddBikeUIViewModel: ViewModel() {
    private var addBikeUIState = mutableStateOf(AddBikeUIState())
    private var allValidationsPassed = mutableStateOf(false)

    fun onAddEvent(event: AddBikeUIEvent, navHostController: NavHostController, db: FirebaseFirestore, context: Context, userLoggedIn: Array<String>) {
        validateWithRules()
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
                if (allValidationsPassed.value) {
                    createBike(db, userLoggedIn)
                    navHostController.navigate(EThessBikeApp.MyBikeList.name)
                } else {
                    Toast.makeText(context, "Some field are completed incorrectly! Make sure you pick a valid color", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validateWithRules() {
        val brandNameResult = Validator.validateBrandName(
            brandName = addBikeUIState.value.brandName
        )

        val colorResult = Validator.validateColor(
            color = addBikeUIState.value.color
        )

        allValidationsPassed.value = brandNameResult && colorResult
    }

    private fun createBike(db: FirebaseFirestore, userLoggedIn: Array<String>) {
        val bike = hashMapOf(
            "brand_name" to addBikeUIState.value.brandName,
            "color" to addBikeUIState.value.color,
            "email" to userLoggedIn[1]
        )

        runBlocking {
            db.collection("bikes")
                .add(bike)
                .await()
        }
    }
}