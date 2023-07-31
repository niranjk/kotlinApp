package com.niranjankhatri.kotlinapp.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.os.*
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niranjankhatri.kotlinapp.R


class PlayerRouteTrackingService : Service() {

    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var serviceHandler: Handler

    override fun onCreate() {
        super.onCreate()
        notificationBuilder = startForegroundService()
        val handlerThread = HandlerThread("PlayerTracking").apply { start() }
        serviceHandler = Handler(handlerThread.looper)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun getPendingIntent() : PendingIntent {
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) FLAG_IMMUTABLE else 0
        return PendingIntent.getActivity(this, 0, Intent(this, WorkManagerActivity::class.java), flag)
    }

    private fun createNotificationChannel(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val newChannelId = "PlayerDeployer"
            val channelName = "Player Depoly Tracking"
            val channel = NotificationChannel(
                newChannelId, channelName, NotificationManager.IMPORTANCE_DEFAULT
            )
            val service = requireNotNull(
                ContextCompat.getSystemService(
                    this,
                    NotificationManager::class.java
                )
            )
            service.createNotificationChannel(channel)
            newChannelId
        } else {
            ""
        }

    private fun startForegroundService(): NotificationCompat.Builder {
        val pendingIntent = getPendingIntent()
        val channelId = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            createNotificationChannel()
        }else ""
        val notificationBuilder = getNotificationBuilder(pendingIntent, channelId)
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        return notificationBuilder
    }

    private fun getNotificationBuilder(pendingIntent: PendingIntent, channelId: String) =
        NotificationCompat.Builder(this, channelId)
            .setContentTitle("Player approching the playground")
            .setContentText("Player deployed")
            .setSmallIcon(R.drawable.ic_travel)
            .setContentIntent(pendingIntent)
            .setTicker("Player deployed, tracking movement")
            .setOngoing(true)

    private fun trackToDestination(notifcationBuilder: NotificationCompat.Builder){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        for (i in 10 downTo 0){
            Thread.sleep(1000L)
            notifcationBuilder.setContentText("$i seconds to the destination").setSilent(true)
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    private fun notifyCompletion(playerId: String){
        Handler(Looper.getMainLooper()).post {
            mutableTrackingcompletion.value = playerId
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val returnValue = super.onStartCommand(intent, flags, startId)
        val playerId = intent?.getStringExtra(EXTRA_PLAYER_ID)?: throw java.lang.IllegalStateException("Player ID must be provided")
        serviceHandler.post {
            trackToDestination(notificationBuilder)
            notifyCompletion(playerId)
            ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
            stopSelf()
        }
        return returnValue
    }

    companion object {
        const val NOTIFICATION_ID = 123
        const val EXTRA_PLAYER_ID = "playerId"
        private val mutableTrackingcompletion = MutableLiveData<String>()
        val trackingCompletion : LiveData<String> = mutableTrackingcompletion
    }
}