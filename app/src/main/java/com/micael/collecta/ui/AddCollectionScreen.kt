package com.micael.collecta.ui

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
import com.micael.collecta.ui.components.BottomNavigationBar

// Cores do formulário
val ColorGreyField = Color(0xFFEFEFEF)
val ColorBlueAccent = Color(0xFF5170FF)
val ColorGreyHeader = Color(0xFFD9D9D9)

data class ProductItem(val name: String = "", val price: String = "", val quantity: Int = 1)

@Composable
fun AddCollectionScreen() {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var collectionDay by remember { mutableStateOf("") }
    var customerPaid by remember { mutableStateOf("") }
    var isPaidCheck by remember { mutableStateOf(false) }
    var localNotification by remember { mutableStateOf(true) }
    
    var productsList by remember { mutableStateOf(listOf(ProductItem())) }

    // Cálculo automático
    val totalProductsValue = productsList.sumOf { (it.price.toDoubleOrNull() ?: 0.0) * it.quantity }
    val paidValue = customerPaid.toDoubleOrNull() ?: 0.0
    val leftValue = totalProductsValue - paidValue

    Scaffold(
        bottomBar = { 
            // Chamando o componente separado
            BottomNavigationBar(
                onAddClick = { /* Já estamos nesta tela */ }
            ) 
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho Adicionar nova cobrança
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorGreyHeader, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Adicionar nova cobrança", fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de entrada
            FormField("Nome", name) { name = it }
            FormField("Data", date) { date = it }
            FormField("Endereço", address) { address = it }
            FormField("Número de telefone", phone) { phone = it }

            Spacer(modifier = Modifier.height(12.dp))

            // Container de Produtos
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorGreyField, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text(
                    "Produtos", 
                    modifier = Modifier.fillMaxWidth(), 
                    textAlign = TextAlign.Center, 
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                
                productsList.forEachIndexed { index, item ->
                    ProductRow(
                        item = item,
                        onUpdate = { updated ->
                            val newList = productsList.toMutableList()
                            newList[index] = updated
                            if (index == productsList.lastIndex && updated.name.isNotEmpty()) {
                                newList.add(ProductItem())
                            }
                            productsList = newList
                        }
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                    Text("Pago?", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Checkbox(
                        checked = isPaidCheck, 
                        onCheckedChange = { isPaidCheck = it },
                        colors = CheckboxDefaults.colors(checkedColor = ColorBlueAccent)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            FormField("Data da cobrança", collectionDay) { collectionDay = it }
            FormField("Cliente pagou", customerPaid) { customerPaid = it }
            
            // Campo Resta (Não editável)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Resta", fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.weight(0.3f))
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                        .background(ColorBlueAccent, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text("R$ %.2f".format(leftValue), color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), 
                verticalAlignment = Alignment.CenterVertically, 
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Notificações de local", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Switch(
                    checked = localNotification, 
                    onCheckedChange = { localNotification = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = ColorBlueAccent)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Lógica de salvar no database */ },
                colors = ButtonDefaults.buttonColors(containerColor = ColorBlueAccent),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Salvar", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FormField(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.weight(0.3f))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(0.7f),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ColorBlueAccent,
                unfocusedContainerColor = ColorBlueAccent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}

@Composable
fun ProductRow(item: ProductItem, onUpdate: (ProductItem) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), 
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = item.name,
            onValueChange = { onUpdate(item.copy(name = it)) },
            modifier = Modifier.weight(0.4f),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Produto", fontSize = 10.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ColorBlueAccent, 
                unfocusedContainerColor = ColorBlueAccent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        TextField(
            value = item.price,
            onValueChange = { onUpdate(item.copy(price = it)) },
            modifier = Modifier.weight(0.3f),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Preço", fontSize = 10.sp) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ColorBlueAccent, 
                unfocusedContainerColor = ColorBlueAccent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Row(
            modifier = Modifier.weight(0.3f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { if(item.quantity > 1) onUpdate(item.copy(quantity = item.quantity - 1)) }) {
                Text("-", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            Text(item.quantity.toString(), fontWeight = FontWeight.Bold)
            IconButton(onClick = { onUpdate(item.copy(quantity = item.quantity + 1)) }) {
                Text("+", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    }
}

