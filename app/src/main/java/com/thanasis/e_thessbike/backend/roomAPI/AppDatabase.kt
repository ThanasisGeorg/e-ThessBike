package com.thanasis.e_thessbike.backend.roomAPI

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Settings::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

fun initNewUserLocalDB(userLoggedIn: Array<String>, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()

    settingsDao.insertSettings(Settings(userId = userLoggedIn[1], theme = "dark"))
    //settingsDao.deleteSettings(Settings(email = "example1@gmail.com"))
    //settingsDao.deleteSettings(Settings(email = "atakas104@gmail.com"))
    Log.d(TAG, settingsDao.getSettings().toString())
}

fun initTheme(roomDb: AppDatabase, onClick: () -> Unit): Boolean {
    val settingsDao = roomDb.settingsDao()
    val isDarkTheme = mutableStateOf(false)

    if (settingsDao.getTheme() == "light") {
        onClick()
    }

    return isDarkTheme.value
}