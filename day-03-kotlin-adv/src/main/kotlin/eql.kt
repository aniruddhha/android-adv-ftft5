package org.example

class Device(
    val devId: String,
    val make: String,
    val isMechanical: Boolean
) {
    override fun equals(other: Any?): Boolean {
        other as Device
        return devId == other.devId
    }

    override fun hashCode(): Int {
        return devId.hashCode()
    }
}

fun main() {
    val d1 = Device("aaa", "abc", true)
    val d2 = Device("aaa", "abc", true)

    println(d1 == d2)
    println(d1.equals(d2))
}