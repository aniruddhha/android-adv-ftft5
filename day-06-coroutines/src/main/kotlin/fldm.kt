package org.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun simpleFlow(): Flow<Int> = flow {
        for (i in 1..100) {
            delay(1000)
            emit(i)
        }
}.flowOn(Dispatchers.IO)
