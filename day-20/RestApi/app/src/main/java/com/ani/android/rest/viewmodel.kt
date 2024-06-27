package com.ani.android.rest

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ApiViewModel(
    private val remoteRepository: RemoteRepository
): ViewModel() {

    val state: Flow<List<Post>> = remoteRepository.getPosts()

    private val _stNrm: MutableStateFlow<List<Post>?> = MutableStateFlow(null)
    val stNrml: StateFlow<List<Post>?> = _stNrm
    fun getPosts() {
        viewModelScope.launch {
//            _stNrm.update {  remoteRepository.getPostNormal() }
        }
    }

    fun callTest() {

        viewModelScope.launch {
            val res = remoteRepository.test()
            Log.i("@ani", "Response $res")
        }

        viewModelScope.launch {
            val txns = remoteRepository.test2()
            txns.forEach { Log.i("@ani", it.toString()) }
        }
    }
}