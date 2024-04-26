package com.thanasis.e_thessbike.backend.roomAPI

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import com.thanasis.e_thessbike.backend.signUp.SignUpViewModel

@Database(entities = [Settings::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}

fun initLocalDB(userLoggedIn: Array<String>, roomDb: AppDatabase) {
    val settingsDao = roomDb.settingsDao()
    val TAG: String? = SignUpViewModel::class.simpleName

    Log.d(TAG, userLoggedIn[1])

    settingsDao.updateSettings(Settings(userLoggedIn[1], "dark"))
    //Log.d(TAG, settingsDao.getSettings().toString())
}