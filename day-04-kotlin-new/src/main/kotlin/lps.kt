package org.example

fun basicLoop() {
    for (i in 1..10) {
        println(i)
    }
}

fun whileLoop() {
    var i = 10
    while (i > 0) {
        i--
        println(i)
    }
}

fun doWhile() {
    var i = 10
    do {
        i--
    } while(i > 0)
}

fun forVariation() {
    for (i in 10 downTo 0) { }

    for (i in 1..10 step 2) { }

    for(i in 0 until 20) { }
}

fun arrLoop(){
    var array = arrayOf(1, 2, 3)

    for( i in array) {

    }
}