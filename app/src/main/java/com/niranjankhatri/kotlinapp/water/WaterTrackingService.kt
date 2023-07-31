package com.niranjankhatri.kotlinapp.water

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.niranjankhatri.kotlinapp.R

class WaterTrackingService : Service() {

    private var fluidBalanceMilliliters = 0f
    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var serviceHandler: Handler

    override fun onCreate() {
        super.onCreate()
        notificationBuilder = startForegroundService()
        val handlerThread = HandlerThread("FluidTracking").apply { start() }
        serviceHandler = Handler(handlerThread.looper)
        updateFluidBalance()
    }

    override fun onDestroy() {
        serviceHandler.removeCallbacksAndMessages(null)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startForegroundService(): NotificationCompat.Builder{
        val pendingIntent = getPendingIntent()
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel()
        } else ""
        val notificationBuilder = getNotificationBuilder(pendingIntent, channelId)
        startForeground(NOTIFICATION_ID, notificationBuilder.build())
        return notificationBuilder
    }

    private fun updateFluidBalance(){
        serviceHandler.postDelayed({
            updateFluidBalance()
            addToFluidBalance(-0.144f)
            notificationBuilder.setContentText(
                "Your fluid balance: %.2f".format(fluidBalanceMilliliters)
            ).setSilent(true)
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }, 5000L)
    }

    private fun addToFluidBalance(amountMilliliters: Float){
        synchronized(this){
            fluidBalanceMilliliters +=amountMilliliters
        }
    }

    private fun getPendingIntent(): PendingIntent {
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else 0
        return PendingIntent.getActivity(this, 0, Intent(this, WaterActivity::class.java), flag)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String{
        val channelId = "FluidBalanceTracking"
        val channelName = "Fluid Balance Tracking"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    private fun getNotificationBuilder(pendingIntent: PendingIntent, channelId: String) =
        NotificationCompat.Builder(this, channelId)
            .setContentTitle("Tracking your fluid balance")
            .setContentText("Tracking")
            .setSmallIcon(R.drawable.ic_water)
            .setContentIntent(pendingIntent)
            .setOngoing(true)


    companion object {
        const val EXTRA_INTAKE_AMOUNT_MILLS = "intake"
        private const val NOTIFICATION_ID = 0X1A2A
    }
}