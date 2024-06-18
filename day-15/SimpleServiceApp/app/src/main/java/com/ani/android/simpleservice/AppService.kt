package com.ani.android.simpleservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AppService : Service() {

    override fun onCreate() {
        super.onCreate()

        Log.i("@ani", "On Create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "On Start Command")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i("@ani", "On Bind")
        return null
    }

    override fun onDestroy() {
        Log.i("@ani", "On Destroy")
        super.onDestroy()
    }
}