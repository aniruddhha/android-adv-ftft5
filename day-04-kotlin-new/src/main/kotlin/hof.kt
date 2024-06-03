package org.example

fun hofDemo() {
    //write a code for checking in array contains 10
    val arr = arrayOf(2, 3,1, 2, 4 ,7)
    val bl = arr.any { itm -> itm == 10 }
    println(bl)

    val bl1 = arr.any(fun (itm: Int): Boolean {
        return itm == 10
    })
}