package com.example.barberia_app.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barberia_app.components.Nav
import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite
import com.example.barberia_app.viewmodels.HorarioViewModel
import com.example.barberia_app.viewmodels.UbicacionViewModel
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun UbicacionScreen(
    ubicacionViewModel: UbicacionViewModel = viewModel(),
    horarioViewModel: HorarioViewModel = viewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToTurnos: () -> Unit = {},
    onNavigateToPerfil: () -> Unit = {}
) {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    // Launcher para pedir permisos de ubicación
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (granted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    ubicacionViewModel.actualizarUbicacion(location.latitude, location.longitude)
                }
            }
        } else {
            ubicacionViewModel.onPermisoDenegado()
        }
    }

    // Verificar permiso al abrir la pantalla
    LaunchedEffect(Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    ubicacionViewModel.actualizarUbicacion(location.latitude, location.longitude)
                }
            }
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // Título
            Text(
                text = "Ubicación",
                color = textWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Recuadro del Mapa con Pin
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1E2229)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación de la barbería",
                    tint = goldPrimary,
                    modifier = Modifier.size(44.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta de Dirección Central
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = surfaseCard)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = ubicacionViewModel.nombreSucursal,
                        color = textMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = ubicacionViewModel.direccion,
                        color = textWhite,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = ubicacionViewModel.zona,
                        color = textMuted,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedButton(
                        onClick = {
                            val direccionEncoded = Uri.encode("${ubicacionViewModel.direccion}, Santiago del Estero")
                            val gmmIntentUri = Uri.parse("google.navigation:q=$direccionEncoded")
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                setPackage("com.google.android.apps.maps")
                            }

                            if (mapIntent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(mapIntent)
                            } else {
                                val webUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$direccionEncoded")
                                context.startActivity(Intent(Intent.ACTION_VIEW, webUri))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFF333333)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = backgroundDark,
                            contentColor = goldPrimary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.NearMe,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cómo llegar en Google Maps",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta de Horarios
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = surfaseCard)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "HORARIOS",
                            color = textMuted,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp
                        )

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (horarioViewModel.estaAbierto) Color(0xFF1A3320) else Color(0xFF331A1A),
                            border = BorderStroke(
                                1.dp,
                                if (horarioViewModel.estaAbierto) Color(0xFF2E5C38) else Color(0xFF5C2E2E)
                            )
                        ) {
                            Text(
                                text = "• ${horarioViewModel.textoEstadoLocal}",
                                color = if (horarioViewModel.estaAbierto) Color(0xFF4CAF50) else Color(0xFFE57373),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    horarioViewModel.listaHorarios.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.dias,
                                color = if (item.destacado) textWhite else textMuted,
                                fontSize = 14.sp,
                                fontWeight = if (item.destacado) FontWeight.Bold else FontWeight.Normal
                            )
                            Text(
                                text = item.horario,
                                color = when {
                                    item.destacado -> goldPrimary
                                    item.horario == "Cerrado" -> textMuted
                                    else -> textWhite
                                },
                                fontSize = 14.sp,
                                fontWeight = if (item.horario == "Cerrado") FontWeight.Normal else FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de WhatsApp
            Button(
                onClick = {
                    val url = "https://wa.me/${ubicacionViewModel.telefonoWhatsApp.replace("+", "")}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF233A28),
                    contentColor = Color(0xFF4CAF50)
                ),
                border = BorderStroke(1.dp, Color(0xFF2E5C38))
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "WhatsApp",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        // Barra de Navegación fija abajo
        Nav(
            rutaActual = "ubicacion",
            onNavigateToHome = onNavigateToHome,
            onNavigateToTurnos = onNavigateToTurnos,
            onNavigateToUbicacion = { },
            onNavigateToPerfil = onNavigateToPerfil,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}