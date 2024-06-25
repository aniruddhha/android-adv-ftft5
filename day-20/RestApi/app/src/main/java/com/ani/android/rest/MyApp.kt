package com.ani.android.rest

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    val restApiService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }
}