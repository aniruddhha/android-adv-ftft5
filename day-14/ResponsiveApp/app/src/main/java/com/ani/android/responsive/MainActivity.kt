package com.ani.android.responsive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.ani.android.responsive.ui.theme.ResponsiveAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ResponsiveAppTheme {

                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                MainComposable(windowSizeClass = windowSizeClass)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(windowSizeClass: WindowSizeClass) {

   if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
       PortraitComposable()
   } else {
       LandscapeComposable()
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortraitComposable() {

    Column {
        Image(painter = painterResource(id = R.drawable.book), contentDescription = "Book Image")
        Text(
            text = "Hello",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandscapeComposable() {
    Row {
        Image(painter = painterResource(id = R.drawable.book), contentDescription = "Book Image")
        Text(
            text = "Hello",
        )
    }
}

