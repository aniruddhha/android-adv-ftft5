package com.ani.android.life.ui

import androidx.lifecycle.ViewModel
import com.google.android.material.badge.BadgeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

data class BlankState(
    val text: String
)
class BlankViewModel : ViewModel() {

    private val _blankState: MutableStateFlow<BlankState> = MutableStateFlow(BlankState(""))
    val blankState: StateFlow<BlankState> = _blankState
}