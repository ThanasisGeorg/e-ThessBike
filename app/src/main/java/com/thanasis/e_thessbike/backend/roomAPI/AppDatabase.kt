package com.thanasis.e_thessbike.backend.roomAPI

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

@Database(entities = [Settings::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

fun initAfterRegister(userLoggedIn: String, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()
    val settings = Settings(userLoggedIn, "dark")

    settingsDao.insertSettings(settings)
}

fun initAfterLogin(userLoggedIn: String, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()
    val settings = Settings(userLoggedIn, settingsDao.getTheme())
    val TAG: String? = SignUpViewModel::class.simpleName

    settingsDao.updateSettings(settings.copy(userId = userLoggedIn))
    Log.d(TAG, "Settings: ${settingsDao.getSettings()}")
}