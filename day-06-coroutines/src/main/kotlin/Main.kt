package org.example

import kotlinx.coroutines.*
import java.io.FileNotFoundException

fun main() {

    println("Hello World!")

    runBlocking { // Coroutine Scope
        launch {
            makeGetCall()
        }
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

suspend fun dispatchers(){
    coroutineScope {

        launch {
            println("1. main runBlocking ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Unconfined) {
            println("2. Unconfined ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("3. Default ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("mythread")) {
            println("4. mythread ${Thread.currentThread().name}")
        }
    }
}

suspend fun confinedDemo() {
    coroutineScope {

        launch(Dispatchers.Unconfined) {
            println("Before Suspention ${Thread.currentThread().name}")
            delay(500)
            println("After Suspention ${Thread.currentThread().name}")
        }

        launch {
            println("before main ${Thread.currentThread().name}")
            delay(1000)
            println("after main ${Thread.currentThread().name}")
        }
    }
}

suspend fun exceptionHandling() {

    val handler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    coroutineScope {

        val job1 = launch(handler) {
            delay(1000)
            throw ArithmeticException("Bad Data")
        }

        val job2 = launch(handler) {
            delay(500)
            throw FileNotFoundException("No File")
        }

        val job3 = launch(handler) {
            println("Works well")
        }

        joinAll(job1, job2, job3)
    }
}