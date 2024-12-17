package com.example.vehicontrol.data.api

import com.example.vehicontrol.domain.model.Nettoyage
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NettoyageService {
    @POST("nettoyage.php")
    suspend fun submitNettoyage(@Body nettoyage: Nettoyage): Response<Void>
}