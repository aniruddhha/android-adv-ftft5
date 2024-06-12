package com.ani.android.life.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class BlankState(
    val text: String
)
class BlankViewModel : ViewModel() {

    private val _blankState: MutableStateFlow<BlankState> = MutableStateFlow(BlankState(""))
    val blankState: StateFlow<BlankState> = _blankState

//    private val _blankState: MutableLiveData<BlankState> = MutableLiveData(BlankState(""))
//    val blankState:LiveData<BlankState> = _blankState

    fun handleClick() {
        _blankState.value = BlankState("Hey Hi")
    }
}