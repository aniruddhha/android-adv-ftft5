package com.ani.android.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.Random

class MyBinderService : Service() {

    private val binder = LocalBinder()

    private val mGen = Random()

//    val randomNumber: Flow<Int>
//        get() = MutableStateFlow<Int>(mGen.nextInt())

    val randomNumber: Int
        get() = mGen.nextInt()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "Service On STart Command")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.i("@ani", "In Service On Bind")
        Toast.makeText(this, "Bind", Toast.LENGTH_SHORT).show()
        return  binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("@ani", "In Service On UnBind")
        Toast.makeText(this, "UnBind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    inner class LocalBinder: Binder() {
        fun getServiceInstance(): MyBinderService = this@MyBinderService
    }
}