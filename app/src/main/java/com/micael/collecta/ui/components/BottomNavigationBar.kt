package com.micael.collecta.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.micael.collecta.R

// Cores fixas do projeto
val ColorGreyMenu = Color(0xFFD9D9D9)
val ColorBlueAccent = Color(0xFF5170FF)

@Composable
fun BottomNavigationBar(
    onMenuClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorGreyMenu)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Esquerda: Quadrado com três linhas
        IconButton(onClick = onMenuClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_rows),
                contentDescription = "Menu",
                tint = ColorBlueAccent,
                modifier = Modifier.size(32.dp)
            )
        }

        // Centro: Círculo com + (ícone maior)
        IconButton(
            onClick = onAddClick,
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_circle),
                contentDescription = "Adicionar",
                tint = ColorBlueAccent,
                modifier = Modifier.size(56.dp)
            )
        }

        // Direita: Engrenagem
        IconButton(onClick = onSettingsClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings_gear),
                contentDescription = "Ajustes",
                tint = ColorBlueAccent,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
