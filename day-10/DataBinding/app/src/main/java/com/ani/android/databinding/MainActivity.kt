package com.ani.android.databinding

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.ani.android.databinding.databinding.ActivityMainBinding

class BtnClickHandler(
    private val lstnr : () -> Unit
) {
    fun onBtnClk() {
        Log.i("@ani","Button Clicked")
        lstnr()
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        findViewById<TextView>(R.id.textView).apply {
//            text = "abc"
//        }

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        val sd = SensorEvent("GPS", "2022-01-01 09:00", true)
        binding.sd = sd
        binding.btnHnd = BtnClickHandler {
            binding.sd = SensorEvent("default", "0000-00-00", false)
        }

    }
}