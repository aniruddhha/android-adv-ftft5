package org.example

import kotlinx.coroutines.*

fun main() {

    println("Hello World!")

    runBlocking { // Coroutine Scope
        concurrency()
    }
    println("Hi Hello")
}

suspend fun basics() {
    coroutineScope {
        launch { // coroutine
            delay(2000)
            println("Launch: Hey hi")
        }

        println("Somewhere Middle")

        async {
            delay(1000)
            println("Async: Hey hi")
        }

        sayHiLater()
    }
}

suspend fun sayHiLater() {
    println("Suspended Function")
    coroutineScope {
        launch {
            delay(1000L)
            println("World")
        }
        println("Hello")
    }
}

suspend fun concurrency() {
    coroutineScope {

        val jb = launch {  }

        val task1 = async {
            delay(2000)
            "task 1: hello"
        }

        val task2 = async {
            delay(1000)
            "task2: hi"
        }

        val res1 = task1.await()
        println("✅ Task1 Completed")
        val res2 = task2.await()
        println("✅ Task2 Completed")

        println("Results $res1, $res2")
    }
}