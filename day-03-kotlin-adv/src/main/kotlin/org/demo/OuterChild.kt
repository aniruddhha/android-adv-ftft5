package org.example.org.demo

import org.example.org.sample.Parent


class OuterChild : Parent() {
    //    override val a = 900; // private not accessible in child
    override val b = 900; // protected accessible in child
    override val c = 7000 // internal accessible in child
    override val d = 800 //  public accessible in child
}

class Accessor(p : Parent) {

    init {
        println(p.d) //  public accessible
        println(p.c) // internal accessible
//        println(p.b) // protected not accessible
//        println(p.a) // private not accessible
    }
}