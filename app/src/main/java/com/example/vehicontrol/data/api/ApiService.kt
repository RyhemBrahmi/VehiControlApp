package com.example.vehicontrol.data.api

import com.example.vehicontrol.domain.model.SuiviVehicule
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("suivi_vehicule.php")
    suspend fun submitSuiviVehicule(@Body suiviVehicule: SuiviVehicule): Response<Void>
}