package com.daniel.estrada.mobilewellnessdapp.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.daniel.estrada.mobilewellnessdapp.R

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(content: String, context: Context) {
    val builder = NotificationCompat.Builder(context, context.getString(R.string.channel_id))
        .setSmallIcon(R.drawable.svg_logo_bowhead)
        .setContentTitle(context.getString(R.string.notification_title))
        .setContentText("New reward received ($content)")
        .setAutoCancel(false)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    notify(NOTIFICATION_ID, builder.build())
}