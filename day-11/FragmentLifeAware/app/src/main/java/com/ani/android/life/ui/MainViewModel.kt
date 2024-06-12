package com.ani.android.life.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val _st: MutableStateFlow<Int> = MutableStateFlow(0)
    val st: StateFlow<Int> = _st

    fun handleMessagesClick() {
        _st.value = 1
    }

    fun handleChatsClick() {
        _st.value = 2
    }

    fun handleCallsClick() {
        _st.value = 3
    }
}