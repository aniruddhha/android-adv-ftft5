package com.ani.android.fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {

    val sharedData: MutableLiveData<Int> = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.let {
            loadFragment(it, R.id.fragmentContainerView, TextFragment())
            loadFragment(it, R.id.fragmentContainerView2, ButtonFragment())
        }
    }
}