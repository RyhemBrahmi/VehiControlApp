package com.example.vehicontrol.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicontrol.domain.model.Nettoyage
import com.example.vehicontrol.domain.repository.NettoyageRepository
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NettoyageViewModel @Inject constructor(
    private val nettoyageRepository: NettoyageRepository
) : ViewModel() {

    // État pour gérer la réponse de l'API
    private val _nettoyageResponse = mutableStateOf<Response<Void>?>(null)
    val nettoyageResponse = _nettoyageResponse

    // État pour gérer les erreurs ou les messages de succès
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage = _errorMessage

    // Fonction pour soumettre les données de nettoyage
    fun submitNettoyage(
        photoBeforeUri: String?,   // URI de la photo avant
        videoBeforeUri: String?,   // URI de la vidéo avant
        photoAfterUri: String?,    // URI de la photo après
        videoAfterUri: String?     // URI de la vidéo après
    ) {
        Log.d("NettoyageViewModel", "Photo Avant URI: $photoBeforeUri, Vidéo Avant URI: $videoBeforeUri, Photo Après URI: $photoAfterUri, Vidéo Après URI: $videoAfterUri")

        val nettoyage = Nettoyage(
            photoBefore = photoBeforeUri ?: "",  // Utiliser l'URI ou une chaîne vide si null
            videoBefore = videoBeforeUri ?: "",  // Utiliser l'URI ou une chaîne vide si null
            photoAfter = photoAfterUri ?: "",    // Utiliser l'URI ou une chaîne vide si null
            videoAfter = videoAfterUri ?: ""     // Utiliser l'URI ou une chaîne vide si null
        )

        viewModelScope.launch {
            // Appel au repository pour envoyer les données
            try {
                val response = nettoyageRepository.submitNettoyage(nettoyage)
                _nettoyageResponse.value = response
                if (response.isSuccessful) {
                    _errorMessage.value = null // Pas d'erreur
                    Log.d("NettoyageViewModel", "Nettoyage enregistré avec succès!")
                } else {
                    _errorMessage.value = "Erreur lors de la soumission des données"
                    Log.d("NettoyageViewModel", "Erreur lors de la soumission des données")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Une erreur est survenue: ${e.message}"
                Log.d("NettoyageViewModel", "Erreur: ${e.message}")
            }
        }
    }

}
