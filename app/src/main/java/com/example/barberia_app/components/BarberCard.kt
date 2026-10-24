package com.example.barberia_app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.barberia_app.ui.theme.borderSubtle
import com.example.barberia_app.ui.theme.surfaseCard

@Composable
fun BarberCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
//ColumnScope=todo lo que ponga dentro ira en vertical
){
    Card(
        modifier = modifier.fillMaxWidth(), // ocupa todo el ancho disponible de la pantalla
        shape = RoundedCornerShape(16.dp), // redondeo de la tarjeta
        colors = CardDefaults.cardColors(
            containerColor = surfaseCard // le ponemos el color al fondo
        ),
        border = BorderStroke(1.dp, borderSubtle), // dibuja una linea al rededor de la tarjeta
        content = content //pasa todo lo que escribimos en BarberCard al interior de la tarjeta
    )
}