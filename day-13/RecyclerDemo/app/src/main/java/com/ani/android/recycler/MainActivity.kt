package com.ani.android.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recVw).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
            adapter = SimpleAdapter(
                listOf(
                    DataItem(R.drawable.baseline_access_time_filled_24, "Time Completed"),
                    DataItem(R.drawable.baseline_account_circle_24, "Account Created"),
                    DataItem(R.drawable.baseline_emoji_emotions_24, "Happy Emoji"),
                    DataItem(R.drawable.baseline_account_circle_24, "Account Deleted"),
                    DataItem(R.drawable.baseline_access_time_filled_24, "Time Remaining"),
                )
            )
        }
    }
}