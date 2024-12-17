package com.example.vehicontrol.domain.usecase

import com.example.vehicontrol.domain.model.Login
import com.example.vehicontrol.domain.model.LoginResponse
import com.example.vehicontrol.domain.repository.LoginRepository
import retrofit2.Response

class SubmitLoginUseCase(private val repository: LoginRepository) {
    suspend fun execute(login: Login): Response<LoginResponse> {
        return repository.submitLogin(login)
    }
}
