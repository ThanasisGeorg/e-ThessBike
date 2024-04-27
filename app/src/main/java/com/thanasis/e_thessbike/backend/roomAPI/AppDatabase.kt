package com.thanasis.e_thessbike.backend.roomAPI

import android.content.ContentValues.TAG
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Settings::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

fun initLocalDB(userLoggedIn: Array<String>, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()

    Log.d(TAG, userLoggedIn[1])
    //settingsDao.insertSettings(Settings(userLoggedIn[1], "dark"))
    if (settingsDao.getSettings() == null) {
        settingsDao.insertSettings(Settings(userLoggedIn[1], "dark"))
    } else settingsDao.updateSettings(Settings(userLoggedIn[1], "dark"))
    Log.d(TAG, settingsDao.getSettings().toString())
}