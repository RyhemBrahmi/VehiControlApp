package com.example.vehicontrol.data.repository

import com.example.vehicontrol.data.api.LoginService
import com.example.vehicontrol.data.db.LoginDao
import com.example.vehicontrol.data.db.LoginEntity
import com.example.vehicontrol.domain.model.Login
import com.example.vehicontrol.domain.model.LoginResponse
import com.example.vehicontrol.domain.repository.LoginRepository
import retrofit2.Response


class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun submitLogin(login: Login): Response<LoginResponse> {
        return loginService.submitLogin(login)
    }
}
