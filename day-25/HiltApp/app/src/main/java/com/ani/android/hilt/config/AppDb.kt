package com.ani.android.hilt.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ani.android.hilt.login.dao.CarDao
import com.ani.android.hilt.login.domain.Car

@Database(
    entities = [Car::class],
    version = 1
)
abstract class AppDb: RoomDatabase() {
    abstract fun carDao(): CarDao
}