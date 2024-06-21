package com.ani.android.contentprovider

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository
): ViewModel() {

    private val _listFlow: MutableStateFlow<List<Contact>?> = MutableStateFlow(null)
    val listFlow: StateFlow<List<Contact>?> = _listFlow

    fun loadContacts() {
        viewModelScope.launch {
            repository.fetchContacts().collect {
                Log.i("@ani", it.toString())
            }
        }
    }

    fun loadContactsNormal() {
        _listFlow.update { repository.fetchContactsList() }
    }
}