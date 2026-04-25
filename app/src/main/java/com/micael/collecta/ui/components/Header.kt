package com.micael.collecta.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micael.collecta.R
import com.micael.collecta.ui.theme.BackgroundGray

@Composable
fun CollectaHeader(userName: String?) {
    // Lógica de saudação: Se o nome estiver nulo ou em branco, exibe apenas "Olá!"
    val greeting = if (userName.isNullOrBlank()) "Olá!" else "Olá, $userName!"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(BackgroundGray)
            .padding(start = 16.dp, end = 4.dp), // Menos padding na direita para o logo ficar rente
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Saudação ao usuário (Agora alinhado à esquerda)
        Text(
            text = greeting,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Logo do App (Alinhado à direita e rente às bordas)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxHeight() // Ocupa a altura total do cabeçalho (60dp)
                .width(60.dp),   // Mantém a proporção quadrada rente às bordas
            contentScale = ContentScale.Fit
        )
    }
}

