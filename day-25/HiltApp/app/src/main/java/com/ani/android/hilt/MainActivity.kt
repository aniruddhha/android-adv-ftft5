package com.ani.android.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ani.android.hilt.login.composable.Login
import com.ani.android.hilt.login.viewmodel.LoginViewModel
import com.ani.android.hilt.ui.theme.HiltAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val carRestApi = (application as MyApp).retrofit.create(CarRestApi::class.java)
//        val carDao = (application as MyApp).db.carDao()
//
//        val remoteRepository = RemoteRepository(carRestApi)
//        val localRepository = LocalRepository(carDao)
//
//        val vm = LoginViewModel(
//            localRepository,
//            remoteRepository
//        )

        setContent {
            HiltAppTheme {
                Login(viewModel)
            }
        }
    }
}
