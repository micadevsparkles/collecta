package com.micael.collecta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micael.collecta.ui.theme.BackgroundGray

@Composable
fun CollectaHeader(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(BackgroundGray) // Fina faixa #FAFAFA [cite: 2]
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo do App (Alinhado à esquerda)
        Text(
            text = "COLLECTA", 
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        
        // Saudação ao usuário
        Text(
            text = "Olá, $userName!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold, // Open Sans Negrito conforme pedido [cite: 2, 5]
            color = Color.Black
        )
    }
}
