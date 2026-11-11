package com.example.barberia_app.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barberia_app.components.BarberButton
import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.borderSubtle
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite
import com.example.barberia_app.viewmodels.NewPasswordViewModel

@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícono circular con borde dorado
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .border(BorderStroke(2.dp, goldPrimary), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (viewModel.correoEnviado) Icons.Default.CheckCircle else Icons.Default.LockReset,
                    contentDescription = "Recuperar contraseña",
                    tint = goldPrimary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (!viewModel.correoEnviado) {
                // Título y subtítulo
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = textWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ingresa tu correo electrónico y te enviaremos un enlace para restablecer tu contraseña.",
                    color = textMuted,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Correo electrónico",
                        color = textWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = viewModel.email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        placeholder = { Text("ejemplo@correo.com", color = textMuted) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Icono email",
                                tint = textMuted
                            )
                        },
                        isError = viewModel.mensajeError != null,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = surfaseCard,
                            unfocusedContainerColor = surfaseCard,
                            focusedBorderColor = goldPrimary,
                            unfocusedBorderColor = borderSubtle,
                            errorBorderColor = Color(0xFFE57373),
                            focusedTextColor = textWhite,
                            unfocusedTextColor = textWhite
                        )
                    )

                    if (viewModel.mensajeError != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = viewModel.mensajeError!!,
                            color = Color(0xFFE57373),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                BarberButton(
                    text = "ENVIAR ENLACE",
                    onClick = { viewModel.enviarEnlace() },
                    enabled = viewModel.botonHabilitado
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿Recordaste tu contraseña? ",
                        color = textMuted,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Inicia sesión",
                        color = goldPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onBackClick() }
                    )
                }
            } else {
                // Estado de confirmación luego de enviar
                Text(
                    text = "¡Correo enviado!",
                    color = textWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Revisa tu bandeja de entrada en ${viewModel.email} y sigue las instrucciones para crear una nueva contraseña.",
                    color = textMuted,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                BarberButton(
                    text = "VOLVER AL INICIO DE SESIÓN",
                    onClick = {
                        viewModel.resetearEstado()
                        onBackClick()
                    }
                )
            }
        }
    }
}