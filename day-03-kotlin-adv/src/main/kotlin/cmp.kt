package org.example

class BookReader(
    var battery: Int,
    var make: String
) {

    companion object {
        val deviceId = "awdfsfg"
    }
}

fun main() {
    val br = BookReader(12, "abc")
    br.battery = 90
    br.make = "pqr"

    println("Companion Object ${BookReader.deviceId}")
}