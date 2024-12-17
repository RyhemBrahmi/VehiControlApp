package com.example.vehicontrol.presentation.viewmodel


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicontrol.domain.model.Login
import com.example.vehicontrol.domain.model.LoginResponse
import com.example.vehicontrol.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn = _isLoggedIn

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage = _errorMessage

    fun submitLogin(email: String, password: String) {
        Log.d("LoginViewModel", "Tentative de login avec email: $email")

        val login = Login(email = email, password = password)

        viewModelScope.launch {
            try {
                // Appel à la méthode du repository pour tenter de se connecter
                val response: Response<LoginResponse> = loginRepository.submitLogin(login)

                if (response.isSuccessful) {
                    // Si la réponse est réussie, on met à jour l'état des connexions
                    val loginResponse = response.body()
                    if (loginResponse?.status == "success") {
                        _isLoggedIn.value = true
                        _errorMessage.value = null
                        Log.d("LoginViewModel", "Login réussi ! Utilisateur ID: ${loginResponse.user_id}")
                    } else {
                        _isLoggedIn.value = false
                        _errorMessage.value = loginResponse?.message ?: "Erreur inconnue"
                    }
                } else {
                    // Si la réponse indique une erreur, on met à jour l'état avec l'erreur
                    _isLoggedIn.value = false
                    _errorMessage.value = "Erreur : ${response.code()} - ${response.message()}"
                    Log.d("LoginViewModel", "Erreur: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // En cas d'exception (par exemple, erreur réseau ou parsing), on met à jour l'état
                _isLoggedIn.value = false
                _errorMessage.value = "Exception : ${e.message}"
                Log.d("LoginViewModel", "Erreur pendant la connexion: ${e.message}")
            }
        }
    }
 /*   fun submitLogin(email: String, password: String) {
        Log.d("LoginViewModel", "Tentative de login avec email: $email")

        val login = Login(email = email, password = password)

        viewModelScope.launch {
            try {
                // Appel à la méthode du repository pour tenter de se connecter
                val response = loginRepository.submitLogin(login)

                if (response.isSuccessful) {
                    // Si la réponse est réussie, on met à jour l'état des connexions
                    _isLoggedIn.value = true
                    _errorMessage.value = null
                    Log.d("LoginViewModel", "Login réussi ! Réponse: ${response.body()}")
                } else {
                    // Si la réponse indique une erreur, on met à jour l'état avec l'erreur
                    _isLoggedIn.value = false
                    _errorMessage.value = "Erreur : ${response.code()} - ${response.message()}"
                    Log.d("LoginViewModel", "Erreur: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // En cas d'exception (par exemple, erreur réseau ou parsing), on met à jour l'état
                _isLoggedIn.value = false
                _errorMessage.value = "Exception : ${e.message}"
                Log.d("LoginViewModel", "Erreur pendant la connexion: ${e.message}")
            }
        }
    }*/


}
