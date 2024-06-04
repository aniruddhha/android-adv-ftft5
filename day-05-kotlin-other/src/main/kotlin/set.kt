package org.example

import java.util.SortedSet
import java.util.TreeSet

fun basicOps() {
    val lst1 = listOf<Int>() // immutable
    val lst2 = mutableListOf<Int>()

    val st1 = HashSet<Int>()

    val st2 = setOf<Int>() // immutable

    val st3 = mutableSetOf<String>()
    st3.add("")

    val st4 = hashSetOf<Int>()
    st4.add(123)
    st4.add(345)
    st4.add(123)

    println(st4)
}

fun advOps() {

    val st1 = hashSetOf<String?>("xyz", "lmn", "abc", "pqr", null, "aqw")

    println(st1)
}