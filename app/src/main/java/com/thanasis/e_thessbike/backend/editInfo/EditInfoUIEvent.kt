package com.thanasis.e_thessbike.backend.editInfo

sealed class EditInfoUIEvent {
    data class FirstNameChanged(val firstName: String): EditInfoUIEvent()
    data class LastNameChanged(val lastName: String): EditInfoUIEvent()
    data object ApplyBtnClicked: EditInfoUIEvent()
}