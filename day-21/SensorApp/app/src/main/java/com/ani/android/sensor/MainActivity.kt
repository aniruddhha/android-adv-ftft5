package com.ani.android.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        val availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
//        availableSensors.forEach { Log.i("@ani", "${it.name}") }

//        Log.i("@ani", "Light ${sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)}")

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            Log.i("@ani", "Sensor Available")
        }

        val light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.i("@ani", "${event?.values?.size}")

        val lux: Float? = event?.let { it.values[0] }
        Log.i("@ani", "$lux")

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}

