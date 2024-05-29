package org.example

// withdraw, deposit, balance
abstract interface AtmDoable {
    abstract fun withdraw(no: String, amt: Double)
    abstract fun deposit(no: String, amt: Double)
    abstract fun balance(no: String)
}

// can be done with any card

// Complete => Incomplete
class CardA : AtmDoable {
    override fun withdraw(no: String, amt: Double) {
       println("Card A Withdraw $no, $amt")
    }

    override fun deposit(no: String, amt: Double) {
        println("Card A Deposit $no, $amt")
    }

    override fun balance(no: String) {
        println("Card A Balance $no")
    }
}

class CardB: AtmDoable {
    override fun withdraw(no: String, amt: Double) {
        println("Card B Withdraw $no, $amt")
    }

    override fun deposit(no: String, amt: Double) {
        println("Card B Deposit $no, $amt")
    }

    override fun balance(no: String) {
        println("Card b Deposit $no")
    }
}

class ATMMachine {
    fun swipeCard(card: AtmDoable) {}
}

fun main() {

    // late binding : Dynamic Polymorphism
    val c1: AtmDoable = CardA()
    c1.balance("aaa")

    val c2: AtmDoable = CardB()
    c2.balance("aaa")

    ATMMachine().swipeCard(c1)
    ATMMachine().swipeCard(c2)
}