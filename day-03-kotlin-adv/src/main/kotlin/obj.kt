package org.example
interface MailExchanger {
    fun sendEmail()
}

fun main() {

    val pcb = object {
        val registers = 10
        val capacitors = 34
        val isChargable = true

        fun recharge() {
            println("Charging Device")
        }

        fun ground() {
            println("Grounding Device")
        }
    }

    println("Chargable ${pcb.isChargable}")
    println("Capacitors ${pcb.capacitors}")
    println("Registers ${pcb.registers}")

    pcb.recharge()
    pcb.ground()

    val me: MailExchanger = object : MailExchanger {
        override fun sendEmail() {

        }
    }
}