package org.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.net.ServerSocket
import java.net.Socket
import java.util.Scanner
import javax.imageio.ImageIO
import kotlin.random.Random

fun main() {
    println("TCP Server")

    runBlocking {
        val server = ServerSocket(9999)
        println("Server running on port ${server.localPort}")

        while (true) {
            launch(Dispatchers.IO) {
                val client = server.accept()
                println("Client connected : ${client.inetAddress.hostAddress}")
                handleClient(client)
            }
        }
    }
}

fun handleClient(client: Socket) {
    println("Handled Client Called")
    val inputStream = client.getInputStream()
    val byt = inputStream.readAllBytes()
    println("Byte Size ${byt.size}")

//    client.close()

//    try {
//        val file = File("img-${Random(1).nextInt()}.jpg")
////        file.outputStream().use { outputStream ->
////            inputStream.copyTo(outputStream)
////        }
//
//        println("Image saved in ${file.name}")
//        inputStream.close()
//        client.close()
//    }catch (e: Exception) {
//        e.printStackTrace()
//    }
}