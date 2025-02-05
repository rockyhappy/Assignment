package com.devrachit.swipeassignment.utility

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.devrachit.swipeassignment.R


class NotificationUtil(val context: Context) {
    val channelId = "UploadNotifications"

    private val notificationChannel = NotificationChannel(
        channelId,
        "Upload Notificaitons",
        NotificationManager.IMPORTANCE_HIGH
    )
    private val manager = context.getSystemService(NotificationManager::class.java)

    fun createNotificationChannel() {
        manager.createNotificationChannel(notificationChannel)
    }

    fun postUploadNotification(id: Int, title: String, body: String) {
        val notification = Notification.Builder(context, channelId)
            .setChannelId(channelId)
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setProgress(0, 100, true)
            .build()

        manager.notify(id, notification)

    }

    fun postSuccessNotification(title: String) {
        val notification = Notification.Builder(context, channelId)
            .setChannelId(channelId)
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(title)
            .build()
        manager.notify(title.length + 1, notification)
    }

    fun cancelNotification(id: Int) {
        manager.cancel(id)
    }


}