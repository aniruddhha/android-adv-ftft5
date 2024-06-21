package com.ani.android.data

import android.app.Application
import android.util.Log
import androidx.room.Room

class MyApp : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this@MyApp,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        db
//        db
//        db
//        db
//        db
        Log.i("@ani", "1 ${db.hashCode()}")
//        Log.i("@ani", "2 ${db.hashCode()}")
//        Log.i("@ani", "3 ${db.hashCode()}")
//        Log.i("@ani", "4 ${db.hashCode()}")
//        Log.i("@ani", "5 ${db.hashCode()}")

    }
}