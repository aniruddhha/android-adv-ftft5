package com.ani.android.hilt

import android.os.Bundle
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
import com.ani.android.hilt.login.composable.Login
import com.ani.android.hilt.login.repository.LocalRepository
import com.ani.android.hilt.login.repository.RemoteRepository
import com.ani.android.hilt.login.rest.CarRestApi
import com.ani.android.hilt.login.viewmodel.LoginViewModel
import com.ani.android.hilt.ui.theme.HiltAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val carRestApi = (application as MyApp).retrofit.create(CarRestApi::class.java)
        val carDao = (application as MyApp).db.carDao()

        val remoteRepository = RemoteRepository(carRestApi)
        val localRepository = LocalRepository(carDao)

        val vm = LoginViewModel(
            localRepository,
            remoteRepository
        )

        setContent {
            HiltAppTheme {
                Login(vm)
            }
        }
    }
}
