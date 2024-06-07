package com.ani.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

   private val scp = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)
        textView.text = "Setting without Co-routine"

        scp.launch {
            delay(2000)
            textView.text = "Changing from code ${mult(12, 89)}"
        }

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
//            textView.text = "Setting on Btn Click ${mult(12, 89)}"
            counter(textView)
        }
    }

    private fun mult(n1: Int, n2: Int) : String {
        return "${n1 * n2}"
    }

    private fun counter(textView: TextView) {
        scp.launch {
            for (i in 10 downTo 1) {
                delay(1000)
                textView.text = "$i"
            }
        }
    }
}