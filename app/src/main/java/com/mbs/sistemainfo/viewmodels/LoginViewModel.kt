package com.mbs.sistemainfo.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    fun validateLogin(user: String, password: String): Boolean {
        return user.trim() == "SISTEMA" && password.trim() == "candidato123"
    }
}