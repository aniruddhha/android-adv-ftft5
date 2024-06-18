package com.ani.android.simpleservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AppService : Service() {

    private val scp: CoroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()

        Log.i("@ani", "On Create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "On Start Command")

        scp.launch {
            // make tcp connection
        }

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