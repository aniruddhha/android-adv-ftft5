package com.ani.android.data

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.ani.android.data.ui.theme.DataAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    fun app() : MyApp = application as MyApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val db = Room.databaseBuilder(
//            this,
//            AppDatabase::class.java,
//            "app_db"
//        ).build()



        val dao = app().db.getVehicleClassDao()

        val repository = VehicleClassRepository(dao)

        val vm = MainViewModel(repository)


        lifecycleScope.launch(Dispatchers.IO) {

//            dao.createNewVehicleClass(
//                VehicleClass(
//                    id = 1,
//                    vehicleClass = "car",
//                    numAxel = 2,
//                    fair = 100.0
//                )
//            )

            dao.getAllClasses().forEach {
                Log.i("@ani", it.toString())
            }
        }

        setContent {
            DataAppTheme {
                DbComposable(vm = vm)
            }
        }
    }
}

@Composable
fun DbComposable(vm : MainViewModel) {

    val state by vm.flowVehicleClasses.collectAsState()

    Column {
        Text(text = "${state.toString()}")
        Button(onClick = { vm.handleClick() }) {
            Text(text = "Fetch All")
        }
    }
}

