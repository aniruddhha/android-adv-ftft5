package com.ani.android.rest

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MyApp: Application() {

    private val retrofit: Retrofit by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com")
            .baseUrl("")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addConverterFactory(
                ScalarsConverterFactory.create()
            )
            .client(okHttp)
            .build()
    }

    private val restApiService: RestApiService by lazy {
        retrofit.create(RestApiService::class.java)
    }

    private val repository: RemoteRepository by lazy {
        RemoteRepository(restApiService)
    }

    val vm: ApiViewModel by lazy {
        ApiViewModel(repository)
    }
}