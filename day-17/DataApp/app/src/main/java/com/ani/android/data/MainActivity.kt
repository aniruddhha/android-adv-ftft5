package com.ani.android.data

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
import androidx.room.Room
import com.ani.android.data.ui.theme.DataAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_db"
        ).build()


        val dao = db.getVehicleClassDao()

        lifecycleScope.launch(Dispatchers.IO) {

            dao.createNewVehicleClass(
                VehicleClass(
                    id = 1,
                    vehicleClass = "car",
                    numAxel = 2,
                    fair = 100.0
                )
            )

            dao.getAllClasses().forEach {
                Log.i("@ani", it.toString())
            }
        }

        setContent {
            DataAppTheme {

            }
        }
    }
}

