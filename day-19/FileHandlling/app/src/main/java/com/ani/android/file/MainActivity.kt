package com.ani.android.file

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.ani.android.file.ui.theme.FileHandllingTheme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       storePrivateExternally()
    }

    private fun storeInternally() {
        val file = File(filesDir, "abc.txt")
        Log.i("@ani", "Internal Storage ${file.path}")

        val data = "Hey Hi How are you"

        openFileOutput("abc.txt", MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    private fun readInternally() {
        val str = openFileInput("abc.txt").bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }

        Log.i("@ani", str)
    }

    private fun storePrivateExternally() {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return

        val storages = ContextCompat.getExternalFilesDirs(this, null)
        storages.forEach { Log.i("@ani", it.path) }

        val data = "Hey Hi, how are you ??"
        val file = File(storages[0],"abc.txt", )

        FileOutputStream(file).use {
            it.write(data.toByteArray())
        }
    }

    private fun readPrivateExternally() {
        TODO("Complete the Code")
    }
}

