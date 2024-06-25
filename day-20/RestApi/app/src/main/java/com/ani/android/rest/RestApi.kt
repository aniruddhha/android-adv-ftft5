package com.ani.android.rest

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @POST("/posts")
    suspend fun create(@Body post: Post): Post
}