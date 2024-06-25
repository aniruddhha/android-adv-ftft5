package com.ani.android.rest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.ani.android.rest.ui.theme.RestApiTheme
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm: ApiViewModel = (application as MyApp).vm

        vm.getPosts()

//        lifecycleScope.launch {
//
//            val savedPost = apiService.create(Post(
//                userId = 10,
//                id = 0,
//                title = "Good Work",
//                body = "It is okay, to work with android"
//            ))
//
//            Log.i("@ani", "🟢 Saved Post 🟢")
//            Log.i("@ani", savedPost.toString())
//        }

        setContent {
            RestApiTheme {
              PostList(vm = vm)
            }
        }
    }
}

