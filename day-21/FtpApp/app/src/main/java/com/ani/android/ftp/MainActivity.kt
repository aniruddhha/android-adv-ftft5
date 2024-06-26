package com.ani.android.ftp

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
import com.ani.android.ftp.ui.theme.FtpAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.net.PrintCommandListener
import org.apache.commons.net.ProtocolCommandListener
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.net.InetAddress
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private lateinit var selectedImageUri: Uri
    private val scp = CoroutineScope(Dispatchers.IO)

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it

            Log.i("@ani", "Selected Image ${selectedImageUri}")

            scp.launch {
                connectToFtp()
            }
        }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FtpAppTheme {
                Button(onClick = { pickImage.launch("image/*") }) {
                    Text(text = "Send to FTP")
                }
            }
        }
    }

    private fun connectToFtp() {
        val ftp = FTPClient()
        ftp.connect("ftp://ftp.dlptest.com/")
        ftp.login("dlpuser", "rNrKYTX9g7z3RgJRmxWuGHbeu")
//        ftp.addProtocolCommandListener(PrintCommandListener(PrintWriter(System.out)))
//        ftp.enterLocalPassiveMode()
        ftp.setFileType(FTP.BINARY_FILE_TYPE)

        val imageByteArray = readImageBytes(selectedImageUri)
        Log.i("@ani", "${imageByteArray?.size}")
        val op: OutputStream = ftp.storeFileStream("img-${Random.nextInt()}.jpg")
        op.write(imageByteArray)

        op.close()
        ftp.logout()
        ftp.disconnect()
    }
}
