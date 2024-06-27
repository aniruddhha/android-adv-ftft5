package com.ani.android.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class BgLocationWorker(
    private val context: Context,
    private val param: WorkerParameters
) :  CoroutineWorker(context, param) {

    private val locationClient = LocationServices.getFusedLocationProviderClient(context)

    companion object {
        val workName = "BgLocationWorker"
        private const val TAG = "BackgroundLocationWork"
    }

    override suspend fun doWork(): Result {

        if(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) return Result.failure()

        locationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            Log.i("@ani", "Lat ${location.latitude} Lng ${location.longitude}")
        }

        return Result.success()
    }
}