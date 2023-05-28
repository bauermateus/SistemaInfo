package com.mbs.sistemainfo.repository

interface LoginRepositoryInterface {
    fun loginWithUserAndPassword(user: String, password: String): Boolean
}