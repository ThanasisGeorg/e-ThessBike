package com.thanasis.e_thessbike.backend.roomAPI

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SettingsDao {
    @Query("SELECT * FROM Settings")
    fun getSettings(): Settings

    @Query("SELECT userId FROM Settings")
    fun getUserId(): String

    @Query("SELECT theme FROM Settings WHERE userId = :emailLoggedIn")
    fun getTheme(emailLoggedIn: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSettings(settings: Settings)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSettings(settings: Settings)

    @Query("DELETE FROM Settings")
    fun deleteSettings()
}