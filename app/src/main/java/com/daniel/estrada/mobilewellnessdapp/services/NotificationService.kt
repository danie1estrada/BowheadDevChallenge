package com.daniel.estrada.mobilewellnessdapp.services

import android.app.NotificationManager
import android.content.Intent
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.util.Log
import androidx.core.content.ContextCompat
import com.daniel.estrada.mobilewellnessdapp.repositories.Repository
import com.daniel.estrada.mobilewellnessdapp.utils.sendNotification

class NotificationService: NotificationListenerService() {

    private var repository: Repository? = null

    override fun onCreate() {
        super.onCreate()
        repository = Repository.getInstance(application)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            repository?.newEarningsEvent()?.subscribe({ event ->
                val nm = ContextCompat.getSystemService(
                    application,
                    NotificationManager::class.java
                ) as NotificationManager
                nm.sendNotification(event.earning.toString(), application)
            }, { err ->
                Log.d("REWARD ERROR", "$err")
            })
        }.start()
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null
}