package com.thanasis.e_thessbike.backend.addBike

sealed class AddBikeUIEvent {
    data class BrandNameChanged(val brandName: String): AddBikeUIEvent()
    data class ColorChanged(val color: String): AddBikeUIEvent()
    data object AddBtnClicked: AddBikeUIEvent()
}