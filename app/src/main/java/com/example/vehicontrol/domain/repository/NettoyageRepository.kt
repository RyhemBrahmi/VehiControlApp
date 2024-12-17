package com.example.vehicontrol.domain.repository

import com.example.vehicontrol.domain.model.Nettoyage
import retrofit2.Response


interface NettoyageRepository {
    suspend fun submitNettoyage(nettoyage: Nettoyage): Response<Void>
}
