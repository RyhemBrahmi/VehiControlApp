package com.example.vehicontrol.data.api

import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.model.Login
import com.example.vehicontrol.domain.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login.php")
    suspend fun submitLogin(@Body login: Login): Response<LoginResponse>
}
