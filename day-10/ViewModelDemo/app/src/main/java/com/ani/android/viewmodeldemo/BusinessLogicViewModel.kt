package com.ani.android.viewmodeldemo

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class BusinessLogicViewModel: ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun setState(us: UiState) {
        _uiState.value = us
    }


    val text: LiveData<String> = uiState.map { it.text } // convert text field to LiveDate
    val checkState: LiveData<Boolean> = uiState.map { it.isChecked }
    val progress: LiveData<Int> =  uiState.map { it.progress }

//    fun getText(): String {
//        return uiState.value?.text ?: "default"
//    }
//
//    fun getCheckState(): Boolean {
//        return uiState.value?.isChecked ?: false
//    }
//
//    fun  getProgress(): Int {
//        return uiState.value?.progress ?: 0
//    }

    fun onBtnClick() {
//        Log.i("@ani", "Called ${getProgress()}")
//        _uiState.value?.let {
//            it.progress = getProgress() + 5
//        }

        _uiState.value?.let {
//           val newState = UiState(text= it.text, isChecked = it.isChecked, progress = it.progress  + 5)
            val newState = it.copy(progress = it.progress  + 5)
            _uiState.postValue(newState)
//            it.progress = it.progress  + 5

        }

//        _uiState.value = UiState("okay", true, 100)
    }
}