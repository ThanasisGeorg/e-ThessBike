package com.thanasis.e_thessbike.backend.roomAPI

import android.content.ContentValues.TAG
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Settings::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

fun initUser(userLoggedIn: Array<String>, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()

    if (settingsDao.getSettings() != null) {
        settingsDao.deleteSettings()
    }
    settingsDao.insertSettings(Settings(userId = userLoggedIn[1], theme = "dark"))
    Log.d(TAG, "Room API: ${settingsDao.getSettings()}")
}