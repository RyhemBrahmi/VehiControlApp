package com.example.vehicontrol.domain.usecase

import com.example.vehicontrol.domain.model.SuiviVehicule
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import retrofit2.Response

class SubmitSuiviVehiculeUseCase(private val repository: SuiviVehiculeRepository) {

    suspend fun execute(suiviVehicule: SuiviVehicule): Response<Void> {
        return repository.submitSuiviVehicule(suiviVehicule)
    }
}