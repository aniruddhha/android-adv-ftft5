package org.example

class Outer {

    private var ot = "hello"

    class Nested

    inner class Inner {
        val inn = 90
        fun innerMethod() = "hi "
    }

    interface InnerInterface

    fun outerMethod() = "outer"
}

fun main() {
    val out: Outer = Outer()
    out.outerMethod()

    val nstd: Outer.Nested = Outer.Nested()
    val inn: Outer.Inner = out.Inner()
}