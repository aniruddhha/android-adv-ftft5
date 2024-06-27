package com.ani.android.rest
import com.google.gson.annotations.SerializedName

data class Post(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
)

data class Transaction(
   val id: Int = 0
)