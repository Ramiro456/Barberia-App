package com.example.barberia_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewPasswordViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var correoEnviado by mutableStateOf(false)
        private set

    var mensajeError by mutableStateOf<String?>(null)
        private set

    // habilita el botón
    val botonHabilitado: Boolean
        get() = email.isNotBlank()

    fun onEmailChange(nuevoEmail: String) {
        email = nuevoEmail
        if (mensajeError != null) {
            mensajeError = null
        }
    }

    fun enviarEnlace() {
        if (!email.contains("@") || !email.contains(".")) {
            mensajeError = "Ingresa un correo electrónico válido"
            return
        }

        // Acá iría la llamada al Repository / Firebase / API en el futuro
        mensajeError = null
        correoEnviado = true
    }

    fun resetearEstado() {
        email = ""
        correoEnviado = false
        mensajeError = null
    }
}