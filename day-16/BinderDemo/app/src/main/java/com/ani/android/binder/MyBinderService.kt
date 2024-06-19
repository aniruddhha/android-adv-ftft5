package com.ani.android.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Random

class MyBinderService : Service() {

    private val binder = LocalBinder()

    private val mGen = Random()

//    val randomNumber: Flow<Int>
//        get() = MutableStateFlow<Int>(mGen.nextInt())

    val randomNumber: Int
        get() =mGen.nextInt()


    override fun onBind(intent: Intent?): IBinder {
        Toast.makeText(this, "Bind", Toast.LENGTH_SHORT).show()
        return  binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "UnBind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    inner class LocalBinder: Binder() {
        fun getServiceInstance(): MyBinderService = this@MyBinderService
    }
}