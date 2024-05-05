package com.thanasis.e_thessbike

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "successfully_add",
            "Add bike",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.description = "A notification"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}