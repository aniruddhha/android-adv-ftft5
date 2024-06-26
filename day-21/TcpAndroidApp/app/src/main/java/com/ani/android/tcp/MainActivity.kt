package com.ani.android.tcp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ani.android.tcp.ui.theme.TcpAndroidAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.Socket

class MainActivity : ComponentActivity() {
    private lateinit var selectedImageUri: Uri
    private lateinit var client: Socket

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it

            Log.i("@ani", "Selected Image ${selectedImageUri}")
            scp.launch {
                val imageBytes = readImageBytes(selectedImageUri)
                Log.i("@ani", "Image Array Size ${imageBytes?.size}")
                imageBytes?.let { bytArr ->
                    sendImageToServer(client, bytArr)
                }
            }
        }
    }

    private val scp = CoroutineScope( Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scp.launch  {
            client = Socket("127.0.0.1", 9999)
        }


        setContent { TcpAndroidAppTheme {
            Button(onClick = { pickImage.launch("image/*") }) {
                Text(text = "Pick Image")
            }
        } }

    }

    private fun readImageBytes(uri: Uri): ByteArray? {
        return try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.readBytes()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun tcp() {

        val client = Socket("127.0.0.1", 9999)
        client.getOutputStream().write("Hello World To Server\n".toByteArray())
//        client.close()
    }

    private fun sendImageToServer(client: Socket, imageBytes: ByteArray) {
        Log.i("@ani", "While Sending ${imageBytes.size}")
        try {
            client.getOutputStream().use {
                it.write(imageBytes)
            }
            Log.i("@ani", "Image Sent Successfully")
//            client.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

