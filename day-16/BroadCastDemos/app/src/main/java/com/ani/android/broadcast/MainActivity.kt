package com.ani.android.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.ani.android.broadcast.ui.theme.BroadCastDemosTheme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class MyViewModel: ViewModel() {

    private val _flow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val flow: StateFlow<Boolean> = _flow

    fun onAirplaneModeChanged(state: Boolean) {
        _flow.update { state }
    }
}

class MyInternalReceiver(
    private val vm: MyViewModel
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("@ani", "Air Plane Mode Changed")

        intent?.let {
            val bndl = it.extras
            bndl?.let {
                val state = bndl.getBoolean("state")
                Log.i("@ani", "State -> $state")

                vm.onAirplaneModeChanged(state)
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = MyViewModel()

        val br = MyInternalReceiver(vm)

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(br, intentFilter)

        setContent {
            BroadCastDemosTheme {
                Airplane(vm)
            }
        }
    }
}

@Composable
fun Airplane(vm: MyViewModel) {

    val state by vm.flow.collectAsState()

    Log.i("@ani", "Rerenderd")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = if(state) R.drawable.baseline_airplanemode_active_24 else R.drawable.baseline_airplanemode_inactive_24),
            contentDescription = "Simple Image Code",
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .align(Alignment.Center)
        )
    }
}

