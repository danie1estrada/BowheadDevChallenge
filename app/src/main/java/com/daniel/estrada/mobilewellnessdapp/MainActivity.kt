package com.daniel.estrada.mobilewellnessdapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.daniel.estrada.mobilewellnessdapp.repositories.Repository
import com.daniel.estrada.mobilewellnessdapp.utils.sendNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Provider
import java.security.Security

class MainActivity : AppCompatActivity() {
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        repository = Repository.getInstance(application)
        setupBouncyCastle()
        createNotificationChannel()

        val sharedPref = getSharedPreferences( getString(R.string.preference_app_data), Context.MODE_PRIVATE)
        if (!sharedPref.getBoolean(getString(R.string.is_first_experience), true))
            listenUserRewards()
    }

    private fun setupBouncyCastle() {
        val provider: Provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)
            ?: // Web3j will set up the provider lazily when it's first used.
            return
        if (provider.javaClass == BouncyCastleProvider::class.java) {
            // BC with same package name, shouldn't happen in real life.
            return
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME)
        Security.insertProviderAt(BouncyCastleProvider(), 1)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // TODO make this a service
    private fun listenUserRewards() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                launch(Dispatchers.IO) {
                    repository.newEarningsEvent()?.subscribe({ event ->
                        val nm = ContextCompat.getSystemService(
                            this@MainActivity,
                            NotificationManager::class.java
                        ) as NotificationManager
                        nm.sendNotification(event.earning.toString(), this@MainActivity)
                    },{ err ->
                        Log.d("EARNING ERROR", "$err")
                    })
                }
            } catch (ex: Exception) {
                Log.d("EARNINGS ERROR", ex.message!!)
            }
        }
    }
}