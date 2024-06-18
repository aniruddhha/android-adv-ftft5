package com.ani.android.simpleservice

import android.app.ForegroundServiceStartNotAllowedException
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat

class MyForegroundService : Service() {
    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startAsForeground()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startAsForeground() {

        try {

            NotificationsHelper.createNotificationChannel(this)

            ServiceCompat.startForeground(
                this,
                123,
               NotificationsHelper.buildNotification(this),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                else 0
            )
        }catch (e: Exception) {
            val ex = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                e is ForegroundServiceStartNotAllowedException
                "problem in creating foreground service"
            } else "Normal Exception"

            Log.e("@ani", ex)
           Log.e("@ani", e.message.toString())
        }
    }
}