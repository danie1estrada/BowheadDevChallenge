package com.daniel.estrada.mobilewellnessdapp.services

import android.content.Intent
import android.os.HandlerThread
import android.os.IBinder
import android.os.Process
import android.service.notification.NotificationListenerService

class NotificationService: NotificationListenerService() {

    override fun onCreate() {
        super.onCreate()
        HandlerThread("", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? = null
}