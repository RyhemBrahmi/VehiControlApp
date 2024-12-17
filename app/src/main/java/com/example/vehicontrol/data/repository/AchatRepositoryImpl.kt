package com.example.vehicontrol.data.repository

import com.example.vehicontrol.data.api.AchatService
import com.example.vehicontrol.data.db.AchatDao
import com.example.vehicontrol.data.db.AchatEntity
import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.repository.AchatRepository
import retrofit2.Response


class AchatRepositoryImpl(
    private val achatService: AchatService,
    private val dao: AchatDao
) : AchatRepository {

    override suspend fun submitAchat(achat: Achat): Response<Void> {
        // Envoie la requête à l'API
        val response = achatService.submitAchat(achat)

        if (response.isSuccessful) {
            // Sauvegarde localement si l'API répond positivement
            dao.insert(
                AchatEntity(
                    montant = achat.montant,
                    quantite = achat.quantite,
                    photoFacture = achat.photoFacture
                )
            )
        }
        return response
    }

}
