package com.ani.android.life.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ani.android.life.ui.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val scp: CoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = MainViewModel()
        val binding: ActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = vm


        scp.launch {
            vm.st.collect {
                Log.i("@Ani", "Int $it")

             when(it) {
                 1 -> loadFragment(BlankFragment())
                 2 -> loadFragment(ChatFragment())
                 3 -> loadFragment(CallsFragment())
             }
            }
        }

    }

    private fun loadFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, frag )
            commit()
        }
    }
}