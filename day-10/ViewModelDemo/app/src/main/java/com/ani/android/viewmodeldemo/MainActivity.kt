package com.ani.android.viewmodeldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ani.android.viewmodeldemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

//        val viewModel:BusinessLogicViewModel by viewModels()
//        val viewModel: BusinessLogicViewModel = ViewModelProvider(this).get(BusinessLogicViewModel::class.java)
        binding.lifecycleOwner = this
        val viewModel: BusinessLogicViewModel = BusinessLogicViewModel()
        viewModel.setState(UiState("Okay", true, 50))

        binding.vm = viewModel

    }
}