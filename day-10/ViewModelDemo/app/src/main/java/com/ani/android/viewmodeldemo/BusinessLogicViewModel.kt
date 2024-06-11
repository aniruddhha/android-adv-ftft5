package com.ani.android.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BusinessLogicViewModel: ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun setState(us: UiState) {
        _uiState.value = us
    }

    fun getText(): String {
        return uiState.value?.text ?: "default"
    }

    fun getCheckState(): Boolean {
        return uiState.value?.isChecked ?: false
    }

    fun  getProgress(): Int {
        return uiState.value?.progress ?: 0
    }
}