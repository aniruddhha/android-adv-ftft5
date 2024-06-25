package com.ani.android.rest

import retrofit2.http.GET

interface RestApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}