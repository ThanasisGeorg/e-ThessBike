package com.thanasis.e_thessbike.backend.roomAPI

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey var userId: String = "",
    @ColumnInfo(name = "theme") var theme: String = "",
    //@ColumnInfo(name = "language") var language: String = "english"
)