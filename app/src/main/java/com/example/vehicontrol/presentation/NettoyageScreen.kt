package com.example.vehicontrol.presentation

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.VideoCameraFront
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vehicontrol.presentation.viewmodel.NettoyageViewModel
import com.example.vehicontrol.ui.theme.Purple40
import com.example.vehicontrol.ui.theme.PurpleGrey80
import com.example.vehicontrol.ui.theme.lexendBoldFont
import com.example.vehicontrol.ui.theme.lexendFont
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NettoyageScreen(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit,
    nettoyageViewModel: NettoyageViewModel = hiltViewModel()

) {
    val snackbarHostState = remember { SnackbarHostState() }

    // États pour stocker les bitmaps capturés par les deux caméras
    val photoBitmapBefore = remember { mutableStateOf<Bitmap?>(null) }
    val photoBitmapAfter = remember { mutableStateOf<Bitmap?>(null) }

    // Camera launchers
    val launcherBefore = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            Log.d("NettoyageScreen", "Photo taken successfully (Before)")
            photoBitmapBefore.value = bitmap
        } else {
            Log.d("NettoyageScreen", "No photo captured (Before)")
        }
    }

    val launcherAfter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            Log.d("NettoyageScreen", "Photo taken successfully (After)")
            photoBitmapAfter.value = bitmap
        } else {
            Log.d("NettoyageScreen", "No photo captured (After)")
        }
    }
    val context = LocalContext.current

    // États pour stocker les Uri des vidéos capturées
    val videoUriBefore = remember { mutableStateOf<Uri?>(null) }
    val videoUriAfter = remember { mutableStateOf<Uri?>(null) }

    // Création des fichiers temporaires pour stocker les vidéos
    val videoFileBefore = remember { File.createTempFile("video_before", ".mp4", context.cacheDir) }
    val videoFileAfter = remember { File.createTempFile("video_after", ".mp4", context.cacheDir) }

    // Launchers pour capturer les vidéos
    val videoLauncherBefore = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success) {
            Log.d("NettoyageScreen", "Video captured successfully (Before)")
            videoUriBefore.value = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFileBefore)
        } else {
            Log.d("NettoyageScreen", "Video capture failed (Before)")
        }
    }

    val videoLauncherAfter = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success) {
            Log.d("NettoyageScreen", "Video captured successfully (After)")
            videoUriAfter.value = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFileAfter)
        } else {
            Log.d("NettoyageScreen", "Video capture failed (After)")
        }
    }

   // États pour gérer les réponses du ViewModel
    val nettoyageResponse = nettoyageViewModel.nettoyageResponse.value
    val errorMessage = nettoyageViewModel.errorMessage.value

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
                text = "Gestion du Nettoyage du Véhicule",
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


            Text(
                text = "Avant nettoyage",
                style = TextStyle(
                    fontFamily = lexendFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W100,
                    lineHeight = 46.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IconButton for Photo
                IconButton(
                    onClick = { launcherBefore.launch() }
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Prendre une photo",
                        tint = Purple40
                    )
                }

                photoBitmapBefore.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Photo capturée (Avant nettoyage)",
                        modifier = Modifier
                            .height(50.dp)
                            .width(60.dp)
                            .border(1.dp, Purple40)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IconButton for Video
                IconButton(
                    onClick = {
                        // Arrêter la vidéo précédente avant de capturer la nouvelle vidéo
                        videoUriBefore.value?.let { uri ->
                            val videoView = android.widget.VideoView(context)
                            videoView.stopPlayback()  // Arrêter la vidéo précédente
                        }

                        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFileBefore)
                        videoLauncherBefore.launch(uri)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoCameraFront,
                        contentDescription = "Prendre une vidéo",
                        tint = Purple40
                    )
                }

                // Affichage de la vidéo capturée avant nettoyage
                videoUriBefore.value?.let { uri ->
                    AndroidView(
                        factory = { context ->
                            android.widget.VideoView(context).apply {
                                setVideoURI(uri)
                                start() // Lancer la vidéo
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(60.dp)
                            .border(1.dp, Purple40)
                    )
                }
            }

            /*  Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceEvenly,
                  verticalAlignment = Alignment.CenterVertically
              ) {

                  // IconButton for Video
                  IconButton(
                      onClick = {
                          val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFileBefore)
                          videoLauncherBefore.launch(uri)
                      }
                  ) {
                      Icon(
                          imageVector = Icons.Default.VideoCameraFront,
                          contentDescription = "Prendre une vidéo",
                          tint = Purple40
                      )
                  }

                  // Affichage de la vidéo capturée avant nettoyage
                  videoUriBefore.value?.let { uri ->
                      AndroidView(
                          factory = { context ->
                              android.widget.VideoView(context).apply {
                                  stopPlayback() // Stopper toute vidéo déjà en cours
                                  setVideoURI(uri)
                                  start()
                              }
                          },
                          modifier = Modifier
                              .height(50.dp)
                              .width(60.dp)
                              .border(1.dp, Purple40)
                      )
                  }
              }
              */
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Après nettoyage",
                style = TextStyle(
                    fontFamily = lexendFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W100,
                    lineHeight = 46.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
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
                    onClick = { launcherAfter.launch() }
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Prendre une photo",
                        tint = Purple40
                    )
                }

                photoBitmapAfter.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Photo capturée (Après nettoyage)",
                        modifier = Modifier
                            .height(50.dp)
                            .width(60.dp)
                            .border(1.dp, Purple40)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Row for Camera and Video Icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IconButton for Video
                IconButton(
                    onClick = {
                        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFileAfter)
                        videoLauncherAfter.launch(uri)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.VideoCameraFront,
                        contentDescription = "Prendre une vidéo",
                        tint = Purple40
                    )
                }
                // Affichage de la vidéo capturée après nettoyage
                videoUriAfter.value?.let { uri ->
                    AndroidView(
                        factory = { context ->
                            android.widget.VideoView(context).apply {
                                setVideoURI(uri)
                                start()
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(60.dp)
                            .border(1.dp, Purple40)
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp)) // Spacer before the button
            // Bouton Enregistrer


            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Log pour vérifier que l'action se déclenche
                Log.d("NettoyageScreen", "Enregistrement des données...")

                // Appel au ViewModel pour soumettre les données
                nettoyageViewModel.submitNettoyage(
                    photoBitmapBefore.value?.toString(),
                    videoUriBefore.value?.toString(),
                    photoBitmapAfter.value?.toString(),
                    videoUriAfter.value?.toString()
                )
            }) {
                Text(text = "Enregistrer")
            }

            Spacer(modifier = Modifier.height(24.dp)) // Spacer before the button



            // Observer les changements dans la réponse
            LaunchedEffect(nettoyageResponse) {
                nettoyageResponse?.let {
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