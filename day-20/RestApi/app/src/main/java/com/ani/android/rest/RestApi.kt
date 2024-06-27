package com.ani.android.rest

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestApiService {
//    @GET("/posts")
//    suspend fun getPosts(): List<Post>

//    @POST("/posts")
//    suspend fun create(@Body post: Post): Post

    @POST("")
    suspend fun test(@Path("num") num: String): String

    @GET("")
    suspend fun test2(): List<Transaction>
}