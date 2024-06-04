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

fun diff() {
    "hello".run {
        println("Length is ${this.length}")
    }

    "hello".let {
        println("Length is ${it.length}")
    }

    val con1 = Connector().apply {
        this.setPort(9090)
        this.setUrl("gty")
    }

    val con2 = Connector().also {
        it.setUrl("ttt")
        it.setPort(8989)
    }

    val con3: String = Connector().let {
        it.setUrl("ttt")
        it.setPort(8989)
//        return "hello" // error
//        return@let "hello"
        "hello"
    }

    val con4: String = Connector().run {
        this.setUrl("ttt")
        this.setPort(8989)
//        return "hello" // error
//        return@let "hello"
        "hello"
    }

    val str2 = with(Connector()) {
        this.setPort(9876)
        this.setUrl("avc")
        return@with "hello"
    }

}