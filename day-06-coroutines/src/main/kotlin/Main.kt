package org.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Hello World!")

    runBlocking { // Coroutine Scope

        launch { // coroutine
            delay(2000)
            println("Launch: Hey hi")
        }

        println("Somewhere Middle")

        async {
            delay(1000)
            println("Async: Hey hi")
        }
    }
    println("Hi Hello")
}