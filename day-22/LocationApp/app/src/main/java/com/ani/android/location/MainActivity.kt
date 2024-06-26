package com.ani.android.location

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import androidx.work.PeriodicWorkRequestBuilder

import com.ani.android.location.ui.theme.LocationAppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private var mService: BbService? = null

    private val conn: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BbService.LocalBinder
            mService = binder.getServiceInstance()

            Log.i("@ani", "In Activity")
            Log.i("@ani", "Lat ${mService?.location?.latitude} Lng: ${mService?.location?.latitude} ")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
        }
    }

    private fun bindToBbService() {
        bindService(Intent(this, BbService::class.java), conn, BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocationAppTheme {

                val cameraPositionState = rememberCameraPositionState() {
                    position = CameraPosition.fromLatLngZoom(LatLng(mService?.location?.latitude ?: 1.35, mService?.location?.longitude ?: 103.87), 10f)
                }

                Column {
                    Button(onClick = { bindToBbService() }) {
                        Text(text = "Get Locations")
                    }

                    Button(onClick = {
                        Log.i("@ani", "Checking New Location")
                        Log.i("@ani", "Lat ${mService?.location?.latitude} Lng: ${mService?.location?.latitude} ")
                    }) {
                        Text(text = "Check New Location ")
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            state = MarkerState(position = LatLng(mService?.location?.latitude ?: 1.35, mService?.location?.longitude ?: 103.87)),
                            title = "Pune",
                            snippet = "Marker on Live Location"

                        )
                    }
                }
            }
        }
    }

    private fun startForegroundService() {
        val intent = Intent(this, BbService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        }
    }
}

@Composable
fun BgLocationAccessScreen() {
    // Request for foreground permissions first
    PermissionBox(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        requiredPermissions = listOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
        onGranted = {
            // From Android 10 onwards request for background permission only after fine or coarse is granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                PermissionBox(permissions = listOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    BackgroundLocationControls()
                }
            } else {
                BackgroundLocationControls()
            }
        },
    )
}

@Composable
private fun BackgroundLocationControls() {
    val context = LocalContext.current
    val workManager = WorkManager.getInstance(context)

    // Component UI state holder
    data class ControlsState(val text: String, val action: String, val onClick: () -> Unit)

    // Observe the worker state to show enable/disable UI
    val workerState by workManager.getWorkInfosForUniqueWorkLiveData(BgLocationWorker.workName)
        .observeAsState()

    val controlsState = remember(workerState) {
        // Find if there is any enqueued or running worker and provide UI state
        val enqueued = workerState?.find { !it.state.isFinished } != null
        if (enqueued) {
            ControlsState(
                text = "Check the logcat for location updates every 15 min",
                action = "Disable updates",
                onClick = {
                    workManager.cancelUniqueWork(BgLocationWorker.workName)
                },
            )
        } else {
            ControlsState(
                text = "Enable location updates and bring the app in the background.",
                action = "Enable updates",
                onClick = {
                    // Schedule a periodic worker to check for location every 15 min
                    workManager.enqueueUniquePeriodicWork(
                        BgLocationWorker.workName,
                        ExistingPeriodicWorkPolicy.KEEP,
                        PeriodicWorkRequestBuilder<BgLocationWorker>(
                            15,
                            TimeUnit.MINUTES,
                        ).build(),
                    )
                },
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = controlsState.text)
        Button(onClick = controlsState.onClick) {
            Text(text = controlsState.action)
        }
    }
}
