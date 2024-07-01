package com.ani.android.hilt.login.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val id: Long,
    val make: String
)