package org.example

import java.util.*

fun dataTypesDemo() {
    println("🟢 Data Types 🟢")

    var num1 = 10
    num1 = 90
    var num2 = 90
    num2 = 89
    println("Num1 $num2")

    val nm: String = "abc"
//    nm = "" // it will produce an error
    val isOkay = false
    println("isOkay $isOkay")
    val height = 190.89
    println("Height $height")
    val dt: Date = Date()
    println("Date $dt")

    val arr1: IntArray = intArrayOf(1, 2, 3, 4)
    println("Array $arr1")

    val arr2 = arrayOf<String>("abc", "pqr", "lmn")
}