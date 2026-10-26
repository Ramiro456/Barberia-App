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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barberia_app.ui.theme.backgroundDark
import com.example.barberia_app.ui.theme.borderSubtle
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted
import com.example.barberia_app.ui.theme.textWhite
import androidx.compose.runtime.setValue
import com.example.barberia_app.components.BarberButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.barberia_app.R
import com.example.barberia_app.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSucess: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel() //instacia del ViewModel
){
    //Variables para guardar lo que el usuario escribe
    //eliminados porque la pantalla ya no almacenará datos
    //var email by remember { mutableStateOf("") }
    //var password by remember { mutableStateOf("") }
    //by: Permite leer y escribir directamente en la variable
    //remember: compose va a recordar este dato cuando la pantalla se redibuje, evitando que el valor vuelva a 0
    //mutableStateOf: crea un valor observable, cada vez que su contenido cambia, Compose actualiza automáticamente los textos en pantalla

    //Box: es un contenedor
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundDark)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ){
        //Column: coloca todos los componentes, en una fila vertical, uno debajo del otro
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(BorderStroke(2.dp, goldPrimary), shape = CircleShape), //circulo para meter la imagen
                contentAlignment = Alignment.Center //se centra en el medio
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_barber_scissors), //se introduce la imagen
                    contentDescription = "Logo Tijeras",
                    tint = goldPrimary,
                    modifier = Modifier.size(54.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Título de la barberia
            Text(
                text = "Barber Shop",
                color = textWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )

            Text(
                text = "Inicia sesión para gestionar tus turnos",
                color = textMuted,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
            )

            //Campo de Email
            OutlinedTextField(
                value = viewModel.email, //lee el valor del viewModel
                onValueChange = { viewModel.onEmailChange(it) }, // it contiene el nuevo texto que escribe el usuario y se lo asigna a email del viewModel
                label = { Text("Correo electrónico") },
                placeholder = { Text("ejemplo@correo.com") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Icono email",
                        tint = textMuted
                    )
                },
                singleLine = true, //impide que el usuario de saltos de linea
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp), //redondea las esquinas del campo de texto
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = surfaseCard,
                    unfocusedContainerColor = surfaseCard,
                    focusedBorderColor = goldPrimary,
                    unfocusedBorderColor = borderSubtle,
                    focusedLabelColor = goldPrimary,
                    unfocusedLabelColor = textMuted,
                    focusedTextColor = textWhite,
                    unfocusedTextColor = textWhite,
                    focusedPlaceholderColor = textMuted
                )
            )

            //Espacio en blanco para separar verticalmente.
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Contraseña") },
                placeholder = { Text("••••••••••••••••") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Icono Candado",
                        tint = textMuted
                    )
                },
                trailingIcon = {//agrega un componente al final, a la derecha, dentro del campo de texto
                    //evalua la variable del viewModel, si es true muestra el ojo abierto, sino el ojo cerrado
                    val icon = if (viewModel.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    //al tocar el icono, le avisa al ViewModel que invierta el estado.
                    IconButton(onClick =  { viewModel.togglePasswordVisible() }) {
                        Icon(imageVector = icon, contentDescription = "Alternar Visibilidad", tint = textMuted)
                    }
                },
                singleLine = true,
                //si es visible muestra el texto tal cual, sino se lo reemplaza por puntitos
                visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), //reemplaza las letras por ••••••••
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = surfaseCard,
                    unfocusedContainerColor = surfaseCard,
                    focusedBorderColor = goldPrimary,
                    unfocusedBorderColor = borderSubtle,
                    focusedLabelColor = goldPrimary,
                    unfocusedLabelColor = textMuted,
                    focusedTextColor = textWhite,
                    unfocusedTextColor = textWhite,
                    focusedPlaceholderColor = textMuted
                )
            )

            // ¿Olvidaste tu contraseña?
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = goldPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {  }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            BarberButton(
                text = "Iniciar Sesión",
                onClick = {viewModel.login(onSucces = onLoginSucess) },
                enabled = viewModel.email.isNotBlank() && viewModel.password.isNotBlank() //el boton estara activo solo si los campos no estan vacios
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Coloca los textos en horizontal, uno al lado del otro
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿No tienes una cuenta? ",
                    color = textMuted,
                    fontSize = 14.sp
                )
                Text(
                    text = "Regístrate",
                    color = goldPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onRegisterClick() } //convierte la palabra Regístrate en un texto clickeable
                )
            }
        }
    }
}