package com.ani.android.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val myAdapter = SimpleAdapter(
            listOf(
                DataItem(R.drawable.baseline_access_time_filled_24, "Time Completed"),
                DataItem(R.drawable.baseline_account_circle_24, "Account Created"),
                DataItem(R.drawable.baseline_emoji_emotions_24, "Happy Emoji"),
                DataItem(R.drawable.baseline_account_circle_24, "Account Deleted"),
                DataItem(R.drawable.baseline_access_time_filled_24, "Time Remaining"),
            )
        )

        findViewById<RecyclerView>(R.id.recVw).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
            adapter = myAdapter
        }

        lifecycleScope.launch {
            myAdapter.channel.collect {
                Toast.makeText(this@MainActivity, it.ttl, Toast.LENGTH_SHORT).show()
                // navigation code
            }
        }
    }
}