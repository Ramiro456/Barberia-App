package com.example.barberia_app.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barberia_app.components.ProfileMenuItem
import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite
import com.example.barberia_app.viewmodels.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onDatosPersonalesClick: () -> Unit = {},
    onMetodosPagoClick: () -> Unit = {},
    onAyudaClick: () -> Unit = {},
    onTerminosClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Título
        Text(
            text = "Mi Perfil",
            color = textWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta de Datos del Usuario
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = surfaseCard)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(2.dp, goldPrimary), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = viewModel.inicialUsuario,
                        color = goldPrimary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = viewModel.nombreUsuario,
                        color = textWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = viewModel.telefono,
                        color = textMuted,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFF2B2414),
                        border = BorderStroke(1.dp, Color(0xFF4A3B18))
                    ) {
                        Text(
                            text = "Cliente Frecuente",
                            color = goldPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta de Opciones de Cuenta
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = surfaseCard)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(
                    icon = Icons.Default.Person,
                    titulo = "Datos personales",
                    onClick = onDatosPersonalesClick
                )

                HorizontalDivider(
                    color = Color(0xFF2A2A2A),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Opción con Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF262626)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = textMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Text(
                        text = "Recordatorios por WhatsApp",
                        color = textWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = viewModel.recordatoriosWhatsApp,
                        onCheckedChange = { viewModel.toggleRecordatorios(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = backgroundDark,
                            checkedTrackColor = goldPrimary,
                            uncheckedThumbColor = textMuted,
                            uncheckedTrackColor = Color(0xFF2A2A2A)
                        )
                    )
                }

                HorizontalDivider(
                    color = Color(0xFF2A2A2A),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileMenuItem(
                    icon = Icons.Default.CreditCard,
                    titulo = "Métodos de pago preferidos",
                    onClick = onMetodosPagoClick
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta de Soporte y Legales
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = surfaseCard)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileMenuItem(
                    icon = Icons.Default.HelpOutline,
                    titulo = "Centro de ayuda y soporte",
                    onClick = onAyudaClick
                )

                HorizontalDivider(
                    color = Color(0xFF2A2A2A),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ProfileMenuItem(
                    icon = Icons.Default.Description,
                    titulo = "Términos y condiciones",
                    onClick = onTerminosClick
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón Cerrar Sesión
        Button(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2D1B1B),
                contentColor = Color(0xFFE57373)
            )
        ) {
            Text(
                text = "Cerrar Sesión",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Espacio para que el Nav inferior no tape el botón
        Spacer(modifier = Modifier.height(100.dp))
    }
}