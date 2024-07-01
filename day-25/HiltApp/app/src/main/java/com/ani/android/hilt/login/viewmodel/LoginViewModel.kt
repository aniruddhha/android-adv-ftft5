package com.ani.android.hilt.login.viewmodel

import androidx.lifecycle.ViewModel
import com.ani.android.hilt.login.repository.LocalRepository
import com.ani.android.hilt.login.repository.RemoteRepository

class LoginViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): ViewModel()