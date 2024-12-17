package com.example.chequeit.presentation.no_permission

import androidx.compose.runtime.Composable
import com.example.vehicontrol.presentation.no_permission.NoPermissionContent

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {

    NoPermissionContent(
        onRequestPermission = onRequestPermission
    )
}
