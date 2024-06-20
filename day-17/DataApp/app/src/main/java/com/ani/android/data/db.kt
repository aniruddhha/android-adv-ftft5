package com.ani.android.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "vehicle_class")
data class VehicleClass(

    @PrimaryKey
    val id: Long,
    val vehicleClass: String,
    val numAxel: Int,
    val fair: Double
)

@Dao
interface VehicleClassDao {
    @Query("select * from vehicle_class")
    fun getAllClasses(): List<VehicleClass>

    @Insert
    fun createNewVehicleClass(cls: VehicleClass)
}

@Database(entities = [VehicleClass::class], version = 1)
abstract class AppDatabase:  RoomDatabase() {
    abstract fun getVehicleClassDao(): VehicleClassDao
}