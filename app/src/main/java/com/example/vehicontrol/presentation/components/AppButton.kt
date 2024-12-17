package com.example.vehicontrol.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vehicontrol.ui.theme.Purple40

@Composable
fun AppButton(
    text: String,
    onClick:  () -> Unit,
    modifier: Modifier = Modifier,
){

    Button(
        onClick = onClick,
        modifier = modifier
            //.width(3.dp)
            .shadow(
                elevation = 20.dp,
                spotColor = Purple40,
                shape = RoundedCornerShape(20.dp)
            )
            .size(width = 180.dp,height = 50.dp),
        shape = RoundedCornerShape(20.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor =Purple40,
            contentColor = Color.White
        ),
        // shape = RoundedCornerShape(20.dp),

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 16.sp,

                )
        )
    }
}
@Composable
fun AppButtonButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color =Color.Red
        )
    }
}