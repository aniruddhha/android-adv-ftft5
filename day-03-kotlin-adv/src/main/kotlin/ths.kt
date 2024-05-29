package org.example

class Context(
    private val num: Int,
    private val str: String
) {

    fun compareState(ctx : Context) {
        println(" Num ${this.num == ctx.num}")
        println(" Str ${this.str == ctx.str}")
    }
}

fun main() {
    val ctx1 = Context(10, "abc")
    val ctx2 = Context(100, "pqr")

    ctx1.compareState(ctx2)
}