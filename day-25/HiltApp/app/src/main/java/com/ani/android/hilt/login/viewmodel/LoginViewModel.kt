package com.ani.android.hilt.login.viewmodel

import androidx.lifecycle.ViewModel
import com.ani.android.hilt.login.repository.LocalRepository
import com.ani.android.hilt.login.repository.RemoteRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): ViewModel()