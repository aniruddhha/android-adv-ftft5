package com.ani.android.hilt

import android.app.Application
import androidx.room.Room
import com.ani.android.hilt.config.AppDb
import com.ani.android.hilt.config.RetrofitClient
import retrofit2.Retrofit


class MyApp: Application() {

    val db: AppDb by lazy {
        Room.databaseBuilder(
            this@MyApp,
            AppDb::class.java,
            "app_db"
        ).build()
    }

    val retrofit: Retrofit by lazy { RetrofitClient.getClient() }
}