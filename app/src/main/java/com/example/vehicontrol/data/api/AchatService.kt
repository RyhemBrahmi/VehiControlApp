package com.example.vehicontrol.data.api

import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.model.SuiviVehicule
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AchatService {
    @POST("achat.php")
    suspend fun submitAchat(@Body achat: Achat): Response<Void>
}
