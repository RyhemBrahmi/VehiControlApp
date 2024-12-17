package com.example.vehicontrol.data.repository

import com.example.vehicontrol.data.api.ApiService
import com.example.vehicontrol.data.db.SuiviVehiculeDao
import com.example.vehicontrol.data.db.SuiviVehiculeEntity
import com.example.vehicontrol.domain.model.SuiviVehicule
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import retrofit2.Response

class SuiviVehiculeRepositoryImpl(
    private val apiService: ApiService,
    private val dao: SuiviVehiculeDao
) : SuiviVehiculeRepository {

    override suspend fun submitSuiviVehicule(suiviVehicule: SuiviVehicule): Response<Void> {
        // Envoie la requête à l'API
        val response = apiService.submitSuiviVehicule(suiviVehicule)

        if (response.isSuccessful) {
            // Sauvegarde localement si l'API répond positivement
            dao.insert(
                SuiviVehiculeEntity(
                    kilometrage = suiviVehicule.kilometrage,
                    carburant = suiviVehicule.carburant,
                    photoPath = suiviVehicule.photoPath,
                    videoPath = suiviVehicule.videoPath
                )
            )
        }
        return response
    }

}


