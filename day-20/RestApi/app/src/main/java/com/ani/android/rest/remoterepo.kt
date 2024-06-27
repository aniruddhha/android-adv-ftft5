package com.ani.android.rest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepository(
    private val apiService: RestApiService
) {
     fun getPosts() : Flow<List<Post>> = flow {
//         emit(apiService.getPosts())
     }

//    suspend fun getPostNormal() = apiService.getPosts()

    suspend fun test() = apiService.test("")

    suspend fun test2() = apiService.test2()
}