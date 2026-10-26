package com.example.barberia_app.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.textMuted

@Composable
fun Categorias(
    categorias: List<String> = listOf("Todos", "Cortes", "Barba", "Combos"),
    categoriaSeleccionada: String,
    onCategoriaSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(categorias) { categoria ->
            val estaSeleccionado = categoria == categoriaSeleccionada

            FilterChip(
                selected = estaSeleccionado,
                onClick = { onCategoriaSelected(categoria) },
                label = {
                    Text(
                        text = categoria,
                        fontSize = 13.sp,
                        fontWeight = if (estaSeleccionado) FontWeight.Bold else FontWeight.Normal
                    )
                },
                shape = RoundedCornerShape(20.dp),
                border = null,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = surfaseCard,
                    labelColor = textMuted,
                    selectedContainerColor = goldPrimary,
                    selectedLabelColor = Color.Black
                )
            )
        }
    }
}