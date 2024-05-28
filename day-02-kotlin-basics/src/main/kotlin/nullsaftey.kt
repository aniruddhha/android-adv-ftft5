package org.example

val nm1 = "abc"
val nm2: String? = null

fun main() {
    println(nm1)
    println("Length of Nm2 ${nm2?.length}") // null safety operator
    println("Value  ${nm2 ?: "na"} ") // elvis operators
    println("Length of Nm2 ${nm2!!.length}") // null assertation
}


