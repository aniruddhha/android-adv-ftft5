package com.ani.android.file

import android.os.Bundle
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
import com.ani.android.file.ui.theme.FileHandllingTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storeInternally()

        readInternally()
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
}

