package com.thanasis.e_thessbike

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    private fun Context.bitmapFromResource(
        @DrawableRes resid: Int
    ) = BitmapFactory.decodeResource(resources, resid)

    fun showBasicNotification() {
        val notification = NotificationCompat
            .Builder(context, "successfully_add")
            .setContentTitle("Successfull add")
            .setContentText("You have successfully added a new bike")
            .setSmallIcon(R.drawable.bike_icon)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}