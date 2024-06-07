package com.ani.android.exp

import android.app.Service
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // starting of becoming activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // partial activity : partially visible
    override fun onStart() {
        super.onStart()
    }

    // complete activity: full visibile
    override fun onResume() {
        super.onResume()
    }

   // start loosing visibility
    override fun onPause() {
        super.onPause()
    }

    // partial activity : partially visible
    override fun onStop() {
        super.onStop()
    }

    // destroyed from memory
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }
}

