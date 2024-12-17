package com.example.vehicontrol.domain.usecase

import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.model.SuiviVehicule
import com.example.vehicontrol.domain.repository.AchatRepository
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import retrofit2.Response

class SubmitAchatUseCase(private val repository: AchatRepository) {
    suspend fun execute(achat: Achat): Response<Void> {
        return repository.submitAchat(achat)
    }
}
