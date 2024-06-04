package org.example

fun basicMapOps() {
    val mp1 = mutableMapOf<Int, String>()
    mp1.put(1, "abc")
    mp1.put(1, "eut")
    mp1[2] = "pqr"

//    mp1.remove(1)

    mp1.keys.forEach { println("Key = $it") }
    mp1.values.forEach { println("Value = $it") }
    mp1.forEach { key, value -> println("Key = $key, Value = $value")  }

    println(mp1)
}

