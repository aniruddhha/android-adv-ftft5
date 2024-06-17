package com.ani.android.composeadv

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ani.android.composeadv.ui.theme.ComposeAdvTheme

class MainActivity : ComponentActivity() {

    private val vm : MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        setContent {
            ComposeAdvTheme {
                CounterWithViewModel(vm)
            }
        }
    }
}

@Composable
fun Counter(msg: String) {

    Log.i("@ani", "Calling Counter")
    val (cnt, setCnt) = remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$cnt",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
//                cnt++
                setCnt( cnt + 1 )
                Log.i("@ani", "Count $cnt")
            }) {
                Text(text = "Okay")
            }
        }
    }
}

@Composable
fun CounterWithViewModel(
    vm : MainViewModel = MainViewModel()
) {
   val state: State<Int> = vm.count.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${state.value}",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                vm.increment()
                Log.i("@ani", "Count ${state.value}")
            }) {
                Text(text = "Okay")
            }
        }
    }
}
