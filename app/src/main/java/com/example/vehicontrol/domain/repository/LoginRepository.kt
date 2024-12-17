package com.example.vehicontrol.domain.repository

import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.model.Login
import com.example.vehicontrol.domain.model.LoginResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun submitLogin(login: Login): Response<LoginResponse>
}
