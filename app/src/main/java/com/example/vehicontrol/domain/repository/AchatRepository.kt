package com.example.vehicontrol.domain.repository

import com.example.vehicontrol.domain.model.Achat
import com.example.vehicontrol.domain.model.SuiviVehicule
import retrofit2.Response

interface AchatRepository {
    suspend fun submitAchat(achat: Achat): Response<Void>
}
