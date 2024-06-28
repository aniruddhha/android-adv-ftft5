package com.ani.android.bluetooth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BtViewModel: ViewModel() {

    private val _state: MutableStateFlow<BtDv?> = MutableStateFlow(null)
    val state: StateFlow<BtDv?> = _state

    fun addDevice(bt: BtDv) {
        _state.update { bt }
    }
}