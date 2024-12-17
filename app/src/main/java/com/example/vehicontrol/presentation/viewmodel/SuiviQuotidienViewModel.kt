package com.example.vehicontrol.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicontrol.domain.model.SuiviVehicule
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SuiviVehiculeViewModel @Inject constructor(
    private val suiviVehiculeRepository: SuiviVehiculeRepository
) : ViewModel() {

    // Etat pour gérer l'envoi des données et la réponse
    private val _suiviVehiculeResponse = mutableStateOf<Response<Void>?>(null)
    val suiviVehiculeResponse = _suiviVehiculeResponse

    // Etat pour gérer les erreurs ou les messages de succès
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage = _errorMessage

    // Fonction pour soumettre le suivi du véhicule
    fun submitSuiviVehicule(
        kilometrage: String,
        carburant: String,
        photoUri: String?,
        videoUri: String?
    ) {
        // Log pour vérifier les valeurs envoyées
        Log.d("SuiviVehiculeViewModel", "Kilométrage: $kilometrage, Carburant: $carburant, Photo URI: $photoUri, Video URI: $videoUri")

        // Crée l'objet SuiviVehicule si les URI sont valides
        val suiviVehicule = SuiviVehicule(
            kilometrage = kilometrage,
            carburant = carburant,
            photoPath = photoUri ?: "",  // Photo URI peut être null, gérer cela
            videoPath = videoUri ?: ""   // Vidéo URI peut être null, gérer cela
        )

        viewModelScope.launch {
            // Appel au repository pour envoyer les données
            try {
                val response = suiviVehiculeRepository.submitSuiviVehicule(suiviVehicule)
                _suiviVehiculeResponse.value = response
                if (response.isSuccessful) {
                    _errorMessage.value = null // Pas d'erreur
                    Log.d("SuiviVehiculeViewModel", "Suivi enregistré avec succès!")
                } else {
                    _errorMessage.value = "Erreur lors de la soumission des données"
                    Log.d("SuiviVehiculeViewModel", "Erreur lors de la soumission des données")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Une erreur est survenue: ${e.message}"
                Log.d("SuiviVehiculeViewModel", "Erreur: ${e.message}")
            }
        }
    }


}