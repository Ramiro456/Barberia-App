package com.example.barberia_app.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.barberia_app.components.home.Categorias
import com.example.barberia_app.components.home.PromoBanner
import com.example.barberia_app.components.home.TarjetaServicio
import com.example.barberia_app.components.Carrito
import com.example.barberia_app.components.Nav

import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite

data class ServicioModel(
    val id: String,
    val titulo: String,
    val detalles: String,
    val precio: String,
    val categoria: String
)

@Composable
fun Home_Catalogo(
    //Aca deberia cargar el nombre del usuario
    nombreUsuario: String = "",
    onNotificationClick: () -> Unit = {},
    onNavigateToTurnos: () -> Unit = {},
    onNavigateToUbicacion: () -> Unit = {},
    onNavigateToPerfil: () -> Unit = {}
) {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

  //Aca hardcodeamos pero deberia recibir data de la base de datos
    var serviciosSeleccionadosIds by remember { mutableStateOf(setOf<String>()) }

    val listaServicios = listOf(
        ServicioModel("1", "Corte Fade / Degradé", "45 min • Tijera y máquina", "$10.000", "Cortes"),
        ServicioModel("2", "Corte Tradicional", "35 min • Solo tijera o máquina", "$7.500", "Cortes"),
        ServicioModel("3", "Perfilado de Barba", "30 min • Navaja", "$5.000", "Barba")
    )

    val serviciosFiltrados = if (categoriaSeleccionada == "Todos") {
        listaServicios
    } else {
        listaServicios.filter { it.categoria == categoriaSeleccionada }
    }

    val serviciosSeleccionados = listaServicios.filter {
        serviciosSeleccionadosIds.contains(it.id)
    }

    val totalMonto = serviciosSeleccionados.sumOf { servicio ->
        servicio.precio.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
    }

    val saludoTexto = if (nombreUsuario.isNotBlank()) "¡Hola, $nombreUsuario!" else "¡Hola!"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Encabezado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(surfaseCard),
                        contentAlignment = Alignment.Center
                    ) {
                        //Aca deberia ir la foto de perfil del usuario
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil del usuario",
                            tint = textMuted,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = saludoTexto,
                            color = textWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "¿Qué servicios buscas hoy?",
                            color = textMuted,
                            fontSize = 13.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(surfaseCard)
                        .clickable { onNotificationClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones",
                        tint = goldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            PromoBanner(
                onAplicarPromoClick = { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Categorias(
                categoriaSeleccionada = categoriaSeleccionada,
                onCategoriaSelected = { categoriaSeleccionada = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(serviciosFiltrados) { servicio ->
                    val estaSeleccionado = serviciosSeleccionadosIds.contains(servicio.id)

                    TarjetaServicio(
                        servicio = servicio,
                        isSelected = estaSeleccionado,
                        onAddClick = {
                            serviciosSeleccionadosIds = if (estaSeleccionado) {
                                serviciosSeleccionadosIds - servicio.id
                            } else {
                                serviciosSeleccionadosIds + servicio.id
                            }
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(if (serviciosSeleccionados.isNotEmpty()) 220.dp else 24.dp))
                }
            }
        }
            if (serviciosSeleccionados.isNotEmpty()) {
                Carrito(
                    serviciosSeleccionados = serviciosSeleccionados,
                    totalMonto = totalMonto,
                    onContinuarClick = { onNavigateToTurnos() },
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 65.dp)
                )
            }

            Nav(
                rutaActual = "home",
                onNavigateToHome = { },
                onNavigateToTurnos = onNavigateToTurnos,
                onNavigateToUbicacion = onNavigateToUbicacion,
                onNavigateToPerfil = onNavigateToPerfil,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
    }
}


