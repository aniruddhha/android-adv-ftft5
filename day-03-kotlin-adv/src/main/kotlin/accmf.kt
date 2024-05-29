package org.example

import org.example.org.sample.Parent

class Child(): Parent() {

//    override val a = 900; // private not accessible in child
    override val b = 900; // protected accessible in child
    override val c = 7000 // internal accessible in child
    override val d = 800 //  public accessible in child
}


class Mobile(
    private var _imei : String,
    private var _mfg: String
) {
    var imei: String
        get() = _imei
        set(value) {
            _imei = value
        }

    override fun toString(): String {
        return "Mobile(_imei='$_imei', _mfg='$_mfg')"
    }
}

fun main() {
    val m = Mobile("rfefg", "sdfs")
    println(m)

    m.imei = "sf" // calling setter
    println(m.imei) // getter function
}