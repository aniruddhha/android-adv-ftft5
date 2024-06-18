package com.ani.android.simpleservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
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
import androidx.compose.ui.tooling.preview.Preview
import com.ani.android.simpleservice.ui.theme.SimpleServiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleServiceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Greeting(this)
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context) {
    Column {


        Button(onClick = {
            val intent = Intent(context, AppService::class.java)
            context.startService(intent)
        }) {
            Text(text = "Start Service")
        }

        Button(onClick = {
            val intent = Intent(context, AppService::class.java)
            val serviceConnection: ServiceConnection = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("@ani", "Bound To Service")
                    Toast.makeText(context, "Bound", Toast.LENGTH_SHORT).show()
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    Log.i("@ani", "unBound from Service")
                    Toast.makeText(context, "UnBound", Toast.LENGTH_SHORT).show()
                }
            }

            context.bindService(intent,serviceConnection ,Context.BIND_AUTO_CREATE)

        }) {
            Text(text = "Bind Service")
        }

    }
}
