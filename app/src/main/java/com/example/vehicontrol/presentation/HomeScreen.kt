package com.example.vehicontrol.presentation

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vehicontrol.ui.theme.Pink40
import com.example.vehicontrol.ui.theme.Purple40
import com.example.vehicontrol.ui.theme.PurpleGrey40
import com.example.vehicontrol.ui.theme.lexendBoldFont
import com.example.vehicontrol.ui.theme.lexendFont

@Composable
fun HomeScreen(
    onNavigateToSuiviQuotidienScreen: () -> Unit,
    onNavigateToNettoyageScreen: () -> Unit,
    onNavigateToCarburantScreen: () -> Unit,

    ) {

        Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), // Padding around the content
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centering content vertically
        ) {
    Text(
        text = "Bienvenue dans l'application de gestion des véhicules",
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
            Spacer(modifier = Modifier.height(20.dp))

            Text(
        text = "Cette application permet aux chauffeurs de suivre l'entretien des véhicules, y compris le suivi quotidien, les achats de carburant et la gestion du nettoyage.",
        style = TextStyle(
            fontFamily = lexendFont,
            fontSize = 17.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 16.25.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(bottom =8.dp)
            .fillMaxWidth()

    )

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                modifier = Modifier
                    .padding(bottom = 16.dp) // Space between the third box and the row
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp), clip = false) // Shadow outside
                   /* .border(
                        width = 0.3.dp,
                        color = Color.Red, // Change color as needed
                        shape = RoundedCornerShape(10.dp)
                    )*/
                    .size(150.dp)
                    .clickable { onNavigateToSuiviQuotidienScreen()  }, // Navigate to another page
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                shape = RoundedCornerShape(10.dp),

            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), // Ensure Box takes full size of the Card
                    contentAlignment = Alignment.Center // Center the content (Icon) inside the Box
                ) {
                    Icon(
                        imageVector = Icons.Default.Timeline, // Example icon
                        contentDescription = "Timeline",
                        modifier = Modifier.size(70.dp),
                        tint = Purple40
                    )
                }


    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Spacing between boxes
        verticalAlignment = Alignment.CenterVertically // Vertically center the row content
    ) {
        // First Box
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp) // Space between the third box and the row
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp), clip = false) // Shadow outside
                /* .border(
                     width = 0.3.dp,
                     color = Color.Red, // Change color as needed
                     shape = RoundedCornerShape(10.dp)
                 )*/
                .size(150.dp)
                .clickable {  onNavigateToNettoyageScreen() }, // Navigate to another page
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(10.dp),

            ) {

            Box(
                modifier = Modifier.fillMaxSize(), // Ensure Box takes full size of the Card
                contentAlignment = Alignment.Center // Center the content (Icon) inside the Box
            ) {
                Icon(
                    imageVector = Icons.Default.CleaningServices, // Example icon
                    contentDescription = "CleaningServices",
                    modifier = Modifier.size(70.dp),
                    tint = PurpleGrey40
                )
            }

        }

        // Second Box
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp) // Space between the third box and the row
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp), clip = false) // Shadow outside
                /* .border(
                     width = 0.3.dp,
                     color = Color.Red, // Change color as needed
                     shape = RoundedCornerShape(10.dp)
                 )*/
                .size(150.dp)
                .clickable { onNavigateToCarburantScreen() }, // Navigate to another page
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = RoundedCornerShape(10.dp),

            ) {
            Box(
                modifier = Modifier.fillMaxSize(), // Ensure Box takes full size of the Card
                contentAlignment = Alignment.Center // Center the content (Icon) inside the Box
            ) {
            // Add an Icon inside the Box
            Icon(
                imageVector = Icons.Default.LocalGasStation, // Example icon
                contentDescription = "Carburant",
                modifier = Modifier.size(70.dp),
                tint = Pink40
            )
            }
        }
    }
}
}
