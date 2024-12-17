package com.example.vehicontrol.presentation.no_permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vehicontrol.presentation.components.AppButton

@Composable
fun NoPermissionContent(
    onRequestPermission: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {

            Text(
                textAlign = TextAlign.Center,
                text = "Veuillez accorder la permission d'utiliser l'appareil photo pour utiliser la fonctionnalité principale de cette application",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
            )

            AppButton(
                text = "Grant permission",
                onClick = onRequestPermission,
                modifier = Modifier
                    // .fillMaxSize()
                    .padding(5.dp)
                    .shadow(
                        elevation = 20.dp,
                        spotColor = Color.Red, // blue
                        shape = RoundedCornerShape(20.dp)
                    )
                    .size(width = 320.dp, height = 50.dp),
            )
        }
    }
}

