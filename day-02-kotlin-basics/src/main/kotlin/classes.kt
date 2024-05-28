package org.example

class Car {
    var speed: Int = 10
    var make: String = "abc"

    fun speedUp(n : Int) {
        this.speed += n
    }

    fun getCarMake() = make

    fun displayDetails() {
        println("Speed $speed, Make $make")
    }
}

class KotlinCar  ( // primary constructor
    var speed: Int = 90,
    var make: String = "abc"
) {
    fun speedUp(n: Int)  {
        this.speed += n
    }

    fun getCarMake() = make

    fun displayDetails() {
        println("Speed $speed, Make $make")
    }
}

class OtherCar {

    var speed: Int
    var make: String

    constructor() { // secondary constructor
        this.speed = 90
        this.make = "abc"
    }
}

fun main() {
    val cr1 = Car()
    cr1.speedUp(10)
    cr1.displayDetails()

    val cr2 = KotlinCar(23, "bbc")
    cr2.displayDetails()
}

