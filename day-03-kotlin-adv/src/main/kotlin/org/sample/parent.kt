package org.example.org.sample

open class Parent {
    private val a: Int = 90
    protected open val b: Int = 100
    internal open val c: Int = 900
    open val d: Int = 1000 // public
}

class Accessor(p : Parent) {

    init {
        println(p.d) //  public accessible
        println(p.c) // internal accessible
//        println(p.b) // protected not accessible
//        println(p.a) // private not accessible
    }
}