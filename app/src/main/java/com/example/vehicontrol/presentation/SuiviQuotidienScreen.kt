package com.example.vehicontrol.presentation

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.VideoCameraFront
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vehicontrol.presentation.viewmodel.SuiviVehiculeViewModel
import com.example.vehicontrol.ui.theme.Purple40
import com.example.vehicontrol.ui.theme.PurpleGrey80
import com.example.vehicontrol.ui.theme.lexendBoldFont
import com.example.vehicontrol.ui.theme.lexendFont
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuiviQuotidienScreen(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit,
    suiviVehiculeViewModel: SuiviVehiculeViewModel = hiltViewModel()

    ) {
    val snackbarHostState = remember { SnackbarHostState() }
   // Observer les erreurs ou les réponses réussies
    val errorMessage by remember { suiviVehiculeViewModel.errorMessage }
    val response by remember { suiviVehiculeViewModel.suiviVehiculeResponse }

    var kilometrageValue  by remember { mutableStateOf("") }
    var carburantValue by remember { mutableStateOf("") }

    val photoBitmap = remember { mutableStateOf<Bitmap?>(null) }

    // Camera launchers
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            Log.d("SuiviQuotidienScreen", "Photo taken successfully")
            photoBitmap.value = bitmap
        } else {
            Log.d("SuiviQuotidienScreen", "No photo captured")
        }
    }


    // Gestion de la réponse du ViewModel
    val suiviVehiculeResponse by remember { suiviVehiculeViewModel.suiviVehiculeResponse }

    val context = LocalContext.current

    // États pour stocker les Uri des vidéos capturées
    val videoUri = remember { mutableStateOf<Uri?>(null) }

    // Création des fichiers temporaires pour stocker les vidéos
    val videoFile = remember { File.createTempFile("video", ".mp4", context.cacheDir) }

    // Launchers pour capturer les vidéos
    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success) {
            Log.d("SuiviQuotidienScreen", "Video captured successfully")
            videoUri.value = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFile)
        } else {
            Log.d("SuiviQuotidienScreen", "Video capture failed")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .padding(20.dp)
                .border(
                    width = 0.3.dp,
                    color = Purple40,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { onNavigateToHomeScreen() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back",
                modifier = Modifier.padding(8.dp)
                    .padding(start = 4.dp)
                //  .align(Alignment.Center),

            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp), // Padding around the content
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Suivi Quotidien du Véhicule",
                style = TextStyle(
                    fontFamily = lexendBoldFont,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 46.5.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp)) // Spacer between fields


            // E-mail TextField
            TextField(
                value = kilometrageValue,
                onValueChange = { kilometrageValue = it },
                label = { Text("Kilométrage ", style = TextStyle(fontFamily = lexendFont)) },
                placeholder = { Text("Entrez le kilométrage actuel", style = TextStyle(fontFamily = lexendFont)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent// Customizing the background color
                )
            )

            Spacer(modifier = Modifier.height(16.dp)) // Spacer between fields

            TextField(
                value = carburantValue,
                onValueChange = { carburantValue = it },
                label = {
                    Text(
                        "Quantité de carburant",
                        style = TextStyle(fontFamily = lexendFont)
                    )
                },
                placeholder = { Text("Entrez la quantité de carburant", style = TextStyle(fontFamily = lexendFont)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent// Customizing the background color
                )
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Row for Camera and Video Icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IconButton for Photo
                IconButton(
                    onClick = { launcher.launch()}
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Prendre une photo",
                        tint = Purple40
                    )
                }

                // IconButton for Video
                IconButton(
                    onClick = {
                        // Arrêter la vidéo précédente avant de capturer la nouvelle vidéo
                        videoUri.value?.let { uri ->
                            val videoView = android.widget.VideoView(context)
                            videoView.stopPlayback()  // Arrêter la vidéo précédente
                        }

                        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFile)
                        videoLauncher.launch(uri)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoCameraFront,
                        contentDescription = "Prendre une vidéo",
                        tint = Purple40
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                photoBitmap.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Photo capturée ",
                        modifier = Modifier
                            .height(50.dp)
                            .width(60.dp)
                            .border(1.dp, Purple40)
                    )
                }

                videoUri.value?.let { uri ->
                    AndroidView(
                        factory = { context ->
                            VideoView(context).apply {
                                setVideoURI(uri)
                                start() // Lancer la vidéo
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(90.dp)
                            .border(1.dp, Purple40)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp)) // Spacer before the button

            // Login Button
          /*  Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Enregistrer")
            }*/
            Button(onClick = {
                // Log pour vérifier que l'action se déclenche
                Log.d("SuiviQuotidienScreen", "Enregistrement des données...")

                // Appel au ViewModel pour soumettre les données
                suiviVehiculeViewModel.submitSuiviVehicule(
                    kilometrageValue,
                    carburantValue,
                    photoBitmap.value?.toString(),
                    videoUri.value?.toString()
                )
            }) {
                Text(text = "Enregistrer")
            }

            Spacer(modifier = Modifier.height(24.dp)) // Spacer before the button


            // Snackbar Host pour afficher les notifications
          //  SnackbarHost(hostState = snackbarHostState)

            // Observer les changements dans la réponse
            LaunchedEffect(suiviVehiculeResponse) {
                suiviVehiculeResponse?.let {
                    if (it.isSuccessful) {
                        snackbarHostState.showSnackbar("Données enregistrées avec succès")
                        onNavigateToHomeScreen()

                    } else {
                        snackbarHostState.showSnackbar("Erreur lors de l'enregistrement des données")
                    }
                }
            }


            // Observer les messages d'erreur
            errorMessage?.let {
                LaunchedEffect(it) {
                    snackbarHostState.showSnackbar("Erreur: $it")
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            //  modifier = Modifier
        ) { snackbarData ->
            Snackbar(
                snackbarData = snackbarData,
                containerColor = PurpleGrey80,
                contentColor = Purple40,
                // modifier = Modifier.padding(16.dp)
            )
        }
    }

}