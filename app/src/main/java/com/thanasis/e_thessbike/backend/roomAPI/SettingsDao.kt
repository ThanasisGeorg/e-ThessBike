package com.thanasis.e_thessbike.backend.roomAPI

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Query("SELECT * FROM Settings")
    fun getSettings(): Settings

    @Query("SELECT userId FROM Settings")
    fun getUserId(): String

    @Query("SELECT theme FROM Settings")
    fun getTheme(): String

    @Insert
    fun insertSettings(vararg settings: Settings)

    @Update
    fun updateSettings(vararg settings: Settings)
}