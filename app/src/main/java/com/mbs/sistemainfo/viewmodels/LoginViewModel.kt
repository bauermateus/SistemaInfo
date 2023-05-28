package com.mbs.sistemainfo.viewmodels

import androidx.lifecycle.ViewModel
import com.mbs.sistemainfo.repository.LoginRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepositoryInterface): ViewModel() {

    fun validateLogin(user: String, password: String): Boolean {
        return repository.loginWithUserAndPassword(user = user, password = password)
    }
}