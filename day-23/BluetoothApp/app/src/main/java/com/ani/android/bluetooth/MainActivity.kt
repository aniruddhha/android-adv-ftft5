package com.ani.android.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Resources.Theme
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.ani.android.bluetooth.ui.theme.BluetoothAppTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.UUID

data class BtDv(
    val nm: String?,
    val addr: String?
)

class MainActivity : ComponentActivity() {

    private val SERVER_NAME = "MyBtServer"
    private val MY_UUID = UUID.fromString("3f8e7ba8-e791-4ab3-b6eb-e7abf21bd379")
    private var bluetoothAdapter: BluetoothAdapter? = null
    private val _flow : Channel<BtDv> = Channel()
    private val flow: Flow<BtDv> = _flow.receiveAsFlow()

    private val btEnableLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK) {
                Log.i("@ani", "You Turned Bt on")
            } else {
                Log.i("@ani", "Your BT is off")
            }
    }

    private val btMakeDiscoverableLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode ==  300) {
            Log.i("@ani", "Your Device is Discoverable")
        } else {
            Log.i("@ani", "You Rejected Device Discovery")
        }
    }

    private val btReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val action = intent.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {

                    val device: BluetoothDevice? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                    } else {
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    }

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.BLUETOOTH_CONNECT
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    device?.let {
                        val nm = it.name
                        val addr = it.address

                        Log.i("@ani", "Nm $nm, Address $addr")
//                        vm.addDevice(BtDv(nm, addr))
                        _flow.trySend(BtDv(nm, addr))
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = BtViewModel()

        BtServer().start()

        lifecycleScope.launch {
            flow.collect {
//                vm.addDevice(this.receive())

                Log.i("@ani", "Activity ${it}")
                vm.addDevice(it)
            }
        }
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            Log.i("@ani", "Bluetooth Not Available")
            return
        }
        Log.i("@ani", "Bluetooth Available")

        setContent {
            BluetoothAppTheme {
                Column {
                    Button(onClick = { discoverDevices(bluetoothAdapter) }) {
                        Text(text = "Discover")
                    }

                    BtDevices(vm = vm)

                }
            }
        }

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(btReceiver, filter)
    }

    override fun onDestroy() {

        unregisterReceiver(btReceiver)
        super.onDestroy()
    }
    private fun enableBt(btAdapter: BluetoothAdapter) {
        if (!btAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            btEnableLauncher.launch(enableBtIntent)
        }
    }

    private fun showPairedDevices(btAdapter: BluetoothAdapter) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val pairedDevices: Set<BluetoothDevice> = btAdapter.bondedDevices
        pairedDevices.forEach { Log.i("@ani", "Nm ${it.name}, Addr ${it.address}") }
        val ourList: List<BtDv> = pairedDevices.map { BtDv(it.name, it.address) }
    }

    private fun discoverDevices(btAdapter: BluetoothAdapter?) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        btAdapter?.startDiscovery()
    }

    private fun makeMeDiscoverable() {
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }

        btMakeDiscoverableLauncher.launch(discoverableIntent)
    }

    inner class BtServer: Thread() {

        private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) null
            else bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord(SERVER_NAME, MY_UUID)
        }

        override fun run() {

            var shouldLoop = true

            while (shouldLoop) {

                val socket: BluetoothSocket? = try {
                    mmServerSocket?.accept()
                } catch (e: IOException) {
                    Log.e("@ani", "Socket's accept() method failed", e)
                    shouldLoop = false
                    null
                }
                socket?.also {
                    manageMyConnectedSocket(it)
                    mmServerSocket?.close()
                    shouldLoop = false
                }
            }
        }

        fun cancel() {
            try {
                mmServerSocket?.close()
            } catch (e: IOException) {
                Log.e("@ani", "Could not close the connect socket", e)
            }
        }

        private fun manageMyConnectedSocket(socket: BluetoothSocket) {
            val inputStream = socket.inputStream
            val outputStream = socket.outputStream

//            val buffer = ByteArray(1024)
//            var numBytes: Int = inputStream.read(buffer)
//            val messageFromClient = String(buffer, 0, numBytes)

                try {
                    val messageFromClient = inputStream.bufferedReader().use { it.readText() }

                    Log.i("@ani", "Message Received From Client")
                    Log.i("@ani", messageFromClient)

                    outputStream.write("Hey We are connected".toByteArray())
                } catch (e: IOException) {
                    Log.e("@ani", "Error while writing the data")
                }
//            }
        }
    }

    inner class BtClient(device: BluetoothDevice): Thread() {

        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(MY_UUID)
        }

        public override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            bluetoothAdapter?.cancelDiscovery()

            mmSocket?.let { socket ->
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                socket.connect()

                // The connection attempt succeeded. Perform work associated with
                // the connection in a separate thread.
//                manageMyConnectedSocket(socket)
            }
        }

        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                Log.e("@ani", "Could not close the client socket", e)
            }
        }

        fun  manageMyConnectedSocket(socket: BluetoothSocket) {
            val inputStream = socket.inputStream
            val outputStream = socket.outputStream

            try {
                outputStream.write("Hey I am client, Just Connected with you".toByteArray())

                val messageFormServer = inputStream.bufferedReader().use{
                    it.readText()
                }

                Log.i("@ani", "Message From Server")
                Log.i("@ani", messageFormServer)
            } catch (e: IOException) {
                Log.e("@ani", "Could not close the client socket", e)
            }
        }
    }
}


@Composable
fun BtDevices(vm: BtViewModel) {

    val state by vm.state.collectAsState()
    val deviceList = remember { mutableStateListOf<BtDv>() }

    LaunchedEffect(state) {
        state?.let { newDevice -> deviceList.add(newDevice) }
    }

    LazyColumn {
        items(deviceList) {
            Column {
                Text(text = it.nm ?: "Not Found")
                Text(text = it.addr ?: "Not Available")
            }
            Divider()
        }
    }
}

