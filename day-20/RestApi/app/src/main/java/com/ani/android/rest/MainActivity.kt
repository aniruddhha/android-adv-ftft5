package com.ani.android.rest

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
import androidx.lifecycle.lifecycleScope
import com.ani.android.rest.ui.theme.RestApiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService: RestApiService = (application as MyApp).restApiService

        lifecycleScope.launch {
            apiService.getPosts().forEach {
                Log.i("@ani", it.toString())
            }
        }

        enableEdgeToEdge()
        setContent {
            RestApiTheme {

            }
        }
    }
}

