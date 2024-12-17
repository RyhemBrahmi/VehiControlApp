package com.example.vehicontrol.data.repository

import com.example.vehicontrol.data.api.NettoyageService
import com.example.vehicontrol.data.db.NettoyageDao
import com.example.vehicontrol.data.db.NettoyageEntity
import com.example.vehicontrol.data.db.SuiviVehiculeEntity
import com.example.vehicontrol.domain.model.Nettoyage
import com.example.vehicontrol.domain.repository.NettoyageRepository
import retrofit2.Response

class NettoyageRepositoryImpl(
    private val nettoyageService: NettoyageService,
    private val dao: NettoyageDao
) : NettoyageRepository {
    override suspend fun submitNettoyage(nettoyage: Nettoyage): Response<Void> {
        // Envoie la requête à l'API
        val response = nettoyageService.submitNettoyage(nettoyage)

        if (response.isSuccessful) {
            // Sauvegarde localement si l'API répond positivement
            dao.insert(
                NettoyageEntity(
                    photoBefore = nettoyage.photoBefore,
                    videoBefore = nettoyage.videoBefore,
                    photoAfter = nettoyage.photoAfter,
                    videoAfter = nettoyage.photoAfter
                )
            )
        }
        return response
    }
}




