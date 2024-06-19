package com.ani.android.binder

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.ani.android.binder.ui.theme.BinderDemoTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MyRepository(
    private val service: MyBinderService
) {
//    val nextInt: Flow<Int> = service.randomNumber

    val nextInt: Int = service.randomNumber
}

class Vm(
    private val repository: MyRepository
) : ViewModel() {

    private val _channel: Channel<Int> = Channel(0)
    val flow: Flow<Int> = _channel.receiveAsFlow()

    fun handleButtonClick(): Int {
        return repository.nextInt
    }
}

class MainActivity : ComponentActivity() {

    private lateinit var  mService: MyBinderService
    private lateinit var vm: Vm
    private val _channel: Channel<Int> = Channel<Int>()
    private val flow: Flow<Int> = _channel.receiveAsFlow()

    private var mBound = false

    private val conn : ServiceConnection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MyBinderService.LocalBinder
            mService = binder.getServiceInstance()
            mBound = true

            Log.i("@ani", "Service Connected")
            val repository = MyRepository(mService)
            vm = Vm(repository)
            _channel.trySend(100)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.i("@ani", "Service DisConnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startService(Intent(this, MyBinderService::class.java))

        lifecycleScope.launch {
            flow.collect {
                Log.i("@ani", "$it")
                if(it == 100) {
                    Log.i("@ani", "Got 100")
                    renderLayout()
                }
            }
        }
    }

    private fun renderLayout() {
        setContent {
            BinderDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(vm)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent(this, MyBinderService::class.java), conn, BIND_AUTO_CREATE
        )
    }

    override fun onStop() {
        unbindService(conn)
        mBound = false
        super.onStop()
    }
}

@Composable
fun Greeting(
    vm: Vm
) {
    val (value, setValue) = remember { mutableIntStateOf(0) }

    Column {
        Text(
            text = "Hello ${value}",
        )
        Button(onClick = { setValue(vm.handleButtonClick()) }) {
            Text(text = "Next")
        }
    }
}
