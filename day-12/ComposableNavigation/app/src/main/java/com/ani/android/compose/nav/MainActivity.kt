package com.ani.android.compose.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ani.android.compose.nav.ui.theme.ComposableNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposableNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination =  "home"
                    ) {
                        composable("home") {
                            Home(navController)
                        }
                        composable(
                            route = "invoices/{invId}",
                            arguments = listOf(
                                navArgument("invId"){
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val invId = it.arguments?.getInt("invId") ?: 0
                            Invoices(invId = invId)
                        }
                        composable("users") {
                            Users()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Home(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate("invoices/100") }) {
            Text(text = "Invoices")
        }

        Button(onClick = { navController.navigate("users") }) {
            Text(text = "Users")
        }
    }
}

@Composable
fun Invoices(invId: Int) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
       Text(text = "Invoices $invId")
    }
}

@Composable
fun Users() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Users")
    }
}


