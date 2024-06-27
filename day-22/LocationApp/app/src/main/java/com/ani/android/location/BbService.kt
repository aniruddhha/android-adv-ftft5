package com.ani.android.location

import android.Manifest
import android.app.Service

import android.app.ForegroundServiceStartNotAllowedException
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import androidx.work.ListenableWorker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class BbService : Service(){

    private val binder = LocalBinder()
    inner class LocalBinder: Binder() {
        fun getServiceInstance(): BbService = this@BbService
    }

    var location: Location? = null

    private lateinit var locationClient : FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()

        startAsForeground()
        locationUpdates()
    }

    override fun onBind(intent: Intent): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "On Start Command Called")

        return super.onStartCommand(intent, flags, startId)
    }

    private fun locationUpdates() {
        if(
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) return

        val req = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 100)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(1000)
            .setMaxUpdateDelayMillis(2000)
            .build()

        locationClient =  LocationServices.getFusedLocationProviderClient(this)

        locationClient.requestLocationUpdates(req, {
            Log.i("@ani", "Location Changed")
            Log.i("@ani", "Lat ${it.latitude} Lng ${it.longitude}")
            this.location = it

        }, null )

        locationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { loc ->

            this.location = loc

            Log.i("@ani", "Current Location")
            Log.i("@ani", "Lat ${loc.latitude} Lng ${loc.longitude}")
        }

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