package org.example

fun withReturnType(): Int {
    return 10
}

fun withParameters(n1: Int, n2: Int): Int {
    return n1 + n2
}

fun withParametersDefaultValues(n1: Int = 90, n2: Int = 10): Int { // default params
    return n1 + n2
}

fun inlineFunction(n1: Int) = n1 * n1

fun main() {
    println(withReturnType())
    println(withParameters(10, 20))
    println(withParametersDefaultValues())
    println(withParametersDefaultValues(100,100))

    println(withParameters(n2 = 90, n1 = 89)) // named parameters
    println(inlineFunction(3))

}