package com.ani.android.data

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: VehicleClassRepository
): ViewModel() {

    private val _channel: Channel<Int> = Channel()
    val flow: Flow<Int> = _channel.receiveAsFlow()

    private val _flowVehicleClasses: MutableStateFlow<List<VehicleClass>> = MutableStateFlow(listOf())
    val flowVehicleClasses: StateFlow<List<VehicleClass>> = _flowVehicleClasses

    private val _flowVehicleClassesV2: Channel<VehicleClass> = Channel()
    val flowVehicleClassesV2: Flow<VehicleClass> = _flowVehicleClassesV2.receiveAsFlow()

    fun handleClick() {
//        _channel.trySend(1)

        viewModelScope.launch(Dispatchers.IO) {
            _flowVehicleClasses.update { repository.getAllClasses() };

//            _flowVehicleClassesV2.update {  }
            repository.getAllClassesV2()
        }
    }

}
