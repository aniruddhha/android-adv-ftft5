package org.example

import kotlinx.coroutines.future.await
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

suspend fun makeGetCall() {
    val client: HttpClient = HttpClient.newBuilder().build()

    val request: HttpRequest = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
        .build()

    val response = client.sendAsync(request, BodyHandlers.ofString())
    val str = response.await().body()
    println(str)
}