package com.ani.android.hilt

import android.app.Application
import androidx.room.Room
import com.ani.android.hilt.config.AppDb
import com.ani.android.hilt.config.RetrofitClient
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit


@HiltAndroidApp
class MyApp: Application() {

//    val db: AppDb by lazy {
//        Room.databaseBuilder(
//            this@MyApp,
//            AppDb::class.java,
//            "app_db"
//        ).build()
//    }

//    val retrofit: Retrofit by lazy { RetrofitClient.getClient() }
}