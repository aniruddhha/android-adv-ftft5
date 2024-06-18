package com.ani.android.simpleservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ServiceCompat
import androidx.lifecycle.lifecycleScope
import com.ani.android.simpleservice.ui.theme.SimpleServiceAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MyViewModel()

        lifecycleScope.launch {
            viewModel.channel.collect {
                when(it) {
                    1 -> startForegroundService()
                    2 -> bindWithService()
                }
            }
        }

        setContent {
            SimpleServiceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Greeting(viewModel)
                }
            }
        }
    }

    private fun startSimpleService() {
        val intent = Intent(this, AppService::class.java)
        startService(intent)
    }

    private fun bindWithService() {
                    val intent = Intent(this, AppService::class.java)
            val serviceConnection: ServiceConnection = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("@ani", "Bound To Service")
                    Toast.makeText(this@MainActivity, "Bound", Toast.LENGTH_SHORT).show()
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("@ani", "unBound from Service")
                    Toast.makeText(this@MainActivity, "UnBound", Toast.LENGTH_SHORT).show()
                }
            }

            bindService(intent,serviceConnection ,Context.BIND_AUTO_CREATE)
    }

    private fun startForegroundService() {
        val intent = Intent(this, MyForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        }
    }
}

@Composable
fun Greeting(viewModel: MyViewModel) {

    Column {
        Button(onClick = {
            viewModel.handleOnClick(1)
        }) {
            Text(text = "Start Service")
        }

        Button(onClick = {
            viewModel.handleOnClick(2)
        }) {
            Text(text = "Bind Service")
        }
    }
}
