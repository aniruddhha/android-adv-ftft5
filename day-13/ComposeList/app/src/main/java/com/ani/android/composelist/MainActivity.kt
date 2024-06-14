package com.ani.android.composelist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ani.android.composelist.ui.theme.ComposeListTheme
import java.util.Date

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                val msgs = listOf(
                    Msg("abc", "Hey hi"),
                    Msg("pqr", "How Are you"),
                    Msg("abc", "I am fine, Thank you")
                )
//                Messages(msgs = msgs)

                MessagesLazy(msgs)
            }
        }
    }
}

data class Msg(
    val from: String,
    val msg: String,
)

@Composable
fun MessageItem(msg: Msg) {
    Column(modifier = Modifier.selectable(
        selected = false,
        onClick = { Log.i("@ani", "${msg.msg}") }
    )) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = msg.from)
            Text(text = msg.msg)
        }
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp))
    }
}

@Composable
fun Messages(msgs: List<Msg>) {
    Column() {
        msgs.forEach {
            MessageItem(msg = it)
        }
    }
}

@Composable
fun MessagesLazy(msgs: List<Msg>) {
    LazyColumn {
//        item {
//            Text(text = "Hey Hi")
//        }
//
//        items(5) {
//            Text(text = "Hey Hi $it")
//        }

        items(msgs) {
            MessageItem(msg = it)
        }
    }
}

