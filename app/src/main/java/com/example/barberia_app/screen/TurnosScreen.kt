package com.example.barberia_app.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite
import com.example.barberia_app.viewmodels.TurnosViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TurnosScreen(
    servicioSeleccionados: List<ServicioModel> = emptyList(),
    totalMonto: Int = 15000,
    viewModel: TurnosViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onConfirmarClick: () -> Unit = {}
) {
    val montoFinal = if (totalMonto > 0) totalMonto else 15000
    val listaServiciosFinal = if (servicioSeleccionados.isNotEmpty()) servicioSeleccionados else emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(surfaseCard)
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = textWhite
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Seleccionar Turno",
                    color = textWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            //Tarjeta de Servicios Seleccionados
            val cantidadServicios =
                if (servicioSeleccionados.isNotEmpty()) servicioSeleccionados.size else 2
            val resumenTexto = if (servicioSeleccionados.isNotEmpty()) {
                servicioSeleccionados.joinToString(" + ") { it.titulo }
            } else {
                "Fade + Barba (75 min)"
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(surfaseCard)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$cantidadServicios Servicios Seleccionados",
                        color = textWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = resumenTexto,
                        color = textMuted,
                        fontSize = 12.sp
                    )
                }
                Text(
                    text = "$totalMonto",
                    color = goldPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Seleccion de profesional
            Text(
                text = "1. Seleccionar Profesional",
                color = textWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.profesionales.forEach { pro ->
                    val esSeleccionado = pro.id == viewModel.profesionalSelecionadoId

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(surfaseCard)
                            .border(
                                width = if (esSeleccionado) 1.5.dp else 0.dp,
                                color = if (esSeleccionado) goldPrimary else surfaseCard,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable { viewModel.onProfesionalSelected(pro.id) }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (pro.esCualquiera) goldPrimary.copy(alpha = 0.2f) else backgroundDark),
                                contentAlignment = Alignment.Center
                            ) {
                                if (pro.esCualquiera) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = goldPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else {
                                    Text(
                                        text = pro.inicial,
                                        color = textMuted,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = pro.nombre,
                                color = if (pro.esCualquiera && esSeleccionado) goldPrimary else textWhite,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = pro.rol,
                                color = if (pro.esCualquiera && esSeleccionado) goldPrimary else textMuted,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Seleccion de fecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "2. Fecha",
                    color = textWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Noviembre 2026",
                    color = textMuted,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(viewModel.diasDisponibles) { dia ->
                    val esSeleccionado = dia == viewModel.diaSeleccionado
                    Box(
                        modifier = Modifier
                            .size(width = 54.dp, height = 65.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (esSeleccionado) goldPrimary else surfaseCard)
                            .clickable { viewModel.onDiaSelected(dia) },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = dia.diaNombre,
                                color = if (esSeleccionado) backgroundDark else textMuted,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = dia.numero,
                                color = if (esSeleccionado) backgroundDark else textWhite,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Seleccion de Horario
            Text(
                text = "3. Horarios Disponibles",
                color = textWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            val chunkedHorarios = viewModel.horariosDisponibles.chunked(4)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                chunkedHorarios.forEach { fila ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        fila.forEach { (hora, disponible) ->
                            val esSeleccionada = hora == viewModel.horaSeleccionada && disponible

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (esSeleccionada) goldPrimary
                                        else if (!disponible) surfaseCard.copy(alpha = 0.5f)
                                        else surfaseCard
                                    )
                                    .clickable(enabled = disponible) { viewModel.onHoraSelected(hora) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = hora,
                                    color = when {
                                        esSeleccionada -> backgroundDark
                                        !disponible -> textMuted.copy(alpha = 0.5f)
                                        else -> textWhite
                                    },
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = if (!disponible) TextDecoration.LineThrough else TextDecoration.None
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //Lista de espera
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(surfaseCard)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.HourglassEmpty,
                    contentDescription = "Reloj de arena",
                    tint = goldPrimary,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "¿Ninguna cita te viene bien?",
                        color = textMuted,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Únete a la lista de espera →",
                        color = goldPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }

        //Boton Confirmar Reserva
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(backgroundDark)
                .padding(16.dp)
        ) {
            Button(
                onClick = { onConfirmarClick() },
                enabled = viewModel.horaSeleccionada != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = goldPrimary,
                    disabledContentColor = surfaseCard
                ),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "CONFIRMAR RESERVA • $$totalMonto",
                    color = backgroundDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

