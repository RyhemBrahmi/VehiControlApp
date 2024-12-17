package com.example.vehicontrol.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.chequeit.presentation.no_permission.NoPermissionScreen
import com.example.vehicontrol.presentation.LoginPage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        onNavigateToHomeScreen = {
            navController.navigate(ScreenRoutes.HomeScreen.route){
                popUpTo(0)
            }
        }
    )
}




@Composable
private fun MainContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    onNavigateToHomeScreen: () -> Unit

) {
    if (hasPermission) {
        LoginPage(onNavigateToHomeScreen)
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}