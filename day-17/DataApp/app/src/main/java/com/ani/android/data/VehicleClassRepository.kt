package com.ani.android.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class VehicleClassRepository(
    private val vehicleClassDao: VehicleClassDao
) {

    suspend fun getAllClasses(): List<VehicleClass> = vehicleClassDao.getAllClasses()

    suspend fun createNewVehicle(vehicle: VehicleClass) = vehicleClassDao.createNewVehicleClass(vehicle)
}