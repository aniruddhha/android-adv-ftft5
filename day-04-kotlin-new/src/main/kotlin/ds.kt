package org.example

import java.util.ArrayDeque
import java.util.Collections
import java.util.PriorityQueue
import java.util.Queue

fun arrBasics() {
    val arr = arrayOf<String>("Abc", "pqr", "lmn", "xyz")

    println("Number of Elements ${arr.size}")
    println("2nd Element ${arr.get(1)}")
    println("2nd Element ${arr[1]}")
}

fun basicsCollection() {
    val col: Collection<Int> = Collections.emptyList()

    val list: List<String> = ArrayList()

    val set: Set<String> = HashSet<String>()

    val que: Queue<Int> = PriorityQueue<Int>()
}

fun listDemo() {
    val list = ArrayList<String>()
    list.add("abc")
    list.add("pqr")
    list.add("lmn")

    list.set(2, "ddd")
    list.remove("ddd")
    println( list.get(1))
    list.forEach { itm ->
        println(itm)
    }
    list.forEach {
        println(it)
    }

    println(list)
}