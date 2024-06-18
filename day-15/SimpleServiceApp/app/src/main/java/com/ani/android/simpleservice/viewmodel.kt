package com.ani.android.simpleservice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class MyViewModel: ViewModel() {

    private val _channel: Channel<Int> = Channel()
    val channel: Flow<Int> = _channel.receiveAsFlow()

    fun handleOnClick(btn: Int) {
        _channel.trySend(btn)
    }
}