package org.example

fun lambdaBasics() {
    // anonymous function
    val fn1: () -> Unit = fun() {

    }
    fn1()

    // lambda function
    val fn2: () -> Unit = {

    }

    val fn3: (num: Int) -> Boolean = { n:Int -> true }
    val fn4: (num: Int) -> Boolean = fun (n: Int): Boolean {
        return true
    }
}

fun returningFunction1(): () -> Boolean {
    return fun(): Boolean {
        return true
    }
}

fun returningFunction2(): (b: Boolean) -> Boolean {
    return { b -> true }
}

fun paramFun() {

    fun caller(fn : () -> Unit) {
        fn()
    }

    caller(fun() {

    })

    caller({  })

    caller() {  }

    caller {

    }
}