package com.micael.collecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micael.collecta.ui.components.CollectaHeader
import com.micael.collecta.ui.components.BottomNavigationBar
// A referência do gráfico permanece a mesma para o próximo arquivo
// import com.micael.collecta.ui.components.CollectionPieChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // 1. Lógica de busca de dados (Integração com SQLite)
    // Aqui simulamos a chamada que virá do seu DatabaseHelper.query("Collections")
    // username deve vir da tabela UserData
    val userName = "Micael" 
    
    // Lista de cobranças vinda da tabela Collections (colunas "name" e "left")
    // Se o retorno do DB for vazio, inicializamos como lista vazia
    val weeklyCollections by remember { mutableStateOf(listOf<Pair<String, String>>()) } 
    
    // Valores de resumo vindos do DB
    val cobradoMes = "" // Ex: "R$ 40.000,00"
    val emDebito = ""   // Ex: "R$ 70.000,00"
    val maisVendido = ""
    val menosVendido = ""

    Scaffold(
        topBar = { CollectaHeader(userName = userName) },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Título: Cobranças da semana
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(Color(0xFFD9D9D9), RoundedCornerShape(12.dp))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Cobranças da semana",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            // Lista de Cobranças (Busca colunas "name" e "left" da tabela Collections)
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp)
            ) {
                if (weeklyCollections.isEmpty()) {
                    Text(
                        "vazio", 
                        modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp), 
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                } else {
                    // Filtra apenas os próximos 7 registros conforme solicitado
                    weeklyCollections.take(7).forEach { (nome, valorRestante) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(vertical = 1.dp)
                                .background(Color(0xFFD9D9D9), RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "$nome • R$ $valorRestante",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Espaço reservado para o Gráfico (Referência: CollectionPieChart)
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color(0xFFF0F0F0), RoundedCornerShape(100.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("CollectionPieChart\n(Aguardando arquivo)", textAlign = TextAlign.Center, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Grid de Resumo (4 Containers Quadrados)
            Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    SummaryCard(
                        "Cobrado este mês", 
                        if (cobradoMes.isEmpty()) "vazio" else cobradoMes, 
                        Modifier.weight(1f), 
                        Color.Black
                    )
                    SummaryCard(
                        "Em débito", 
                        if (emDebito.isEmpty()) "vazio" else emDebito, 
                        Modifier.weight(1f), 
                        Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    SummaryCard(
                        "Produto mais vendido", 
                        if (maisVendido.isEmpty()) "vazio" else maisVendido, 
                        Modifier.weight(1f), 
                        Color.Black
                    )
                    SummaryCard(
                        "Produto menos vendido", 
                        if (menosVendido.isEmpty()) "vazio" else menosVendido, 
                        Modifier.weight(1f), 
                        Color.Black
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SummaryCard(title: String, value: String, modifier: Modifier, valueColor: Color) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(Color(0xFFD9D9D9), RoundedCornerShape(12.dp))
            .padding(12.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                value,
                fontSize = if (value == "vazio") 14.sp else 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (value == "vazio") Color.Gray else valueColor,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}
