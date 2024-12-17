package com.example.vehicontrol.domain.usecase

import com.example.vehicontrol.domain.model.Nettoyage
import com.example.vehicontrol.domain.repository.NettoyageRepository
import retrofit2.Response

class SubmitNettoyageUseCase(private val repository: NettoyageRepository) {
    suspend fun execute(nettoyage: Nettoyage): Response<Void> {
        return repository.submitNettoyage(nettoyage)
    }
}
