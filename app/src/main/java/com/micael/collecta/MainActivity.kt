package com.micael.collecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micael.collecta.ui.components.CollectaHeader
import com.micael.collecta.ui.theme.BackgroundGray
import com.micael.collecta.ui.theme.ContainerGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { CollectaHeader(userName = "Micael") },
        bottomBar = { NavigationFooter() },
        containerColor = BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título: Cobranças para esta semana [cite: 2, 11]
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = ContainerGray
            ) {
                Text(
                    text = "Cobranças para esta semana",
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Simulação dos mini containers com contorno preto [cite: 3, 14]
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(60.dp, 30.dp)
                            .background(ContainerGray)
                            .border(BorderStroke(1.dp, Color.Black))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Métricas da Página 3 [cite: 5]
            MetricRow("Cobrado este mês", "R$ 40.000,00")
            MetricRow("Em débito", "R$ 70.000,00")
            MetricRow("Produto mais vendido", "BodySplash Olympic")
            MetricRow("Produto menos vendido", "Beard and hair oil")
        }
    }
}

@Composable
fun MetricRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(ContainerGray, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun NavigationFooter() {
    // Implementação básica do rodapé fixo [cite: 4, 15]
    BottomAppBar(
        containerColor = Color.White,
        actions = {
            // Aqui entrarão os ícones de Detalhes e Configurações
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Abrir Formulário */ }) {
                Text("+", fontSize = 24.sp)
            }
        }
    )
}
