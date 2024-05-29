package org.example

abstract class Tab {
    abstract fun draw()
    open fun isTouchScreen() = false
}

class MobileTablet : Tab() {
    override fun draw() {
        println("Drawing on Mobile")
    }

    override fun isTouchScreen(): Boolean = true
}

class PenTablet : Tab() {
    override fun draw() {
        println("Drawing on Pen Tab")
    }

    override fun isTouchScreen(): Boolean = false
}
