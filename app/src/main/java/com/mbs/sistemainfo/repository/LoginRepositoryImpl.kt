package com.mbs.sistemainfo.repository

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(): LoginRepositoryInterface {

    override fun loginWithUserAndPassword(user: String, password: String): Boolean {
        return user.trim() == "SISTEMA" && password.trim() == "canditado123"
    }

}