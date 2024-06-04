package org.example

class Connector {
    private var port: Int = 0
    private lateinit var url: String

    fun getPort(): Int {
        return port
    }

    fun setPort(port: Int) {
        this.port = port
    }

    fun getUrl() : String = url

    fun setUrl(url: String) {
        this.url = url
    }
}

fun intro() {
//    val str = "hello"
//    val sts = str.isEmpty() && str.isBlank()
//    println(sts)

    val sts = "hello".let {
        it.isEmpty() && it.isBlank()
    }
    println(sts)

    // let, run, with, apply, also
}

fun advScp() {
    val con1 = Connector()
    con1.setPort(9090)
    con1.setUrl("abc")
    println(con1.getPort())
    println(con1.getUrl())

    Connector().apply {
        setPort(9090)
        setUrl("acx")
        println(getPort())
        println(getUrl())
    }

}