package com.example.vehicontrol.domain.repository

import com.example.vehicontrol.domain.model.SuiviVehicule
import retrofit2.Response

interface SuiviVehiculeRepository {
    suspend fun submitSuiviVehicule(suiviVehicule: SuiviVehicule): Response<Void>
}