package com.example.barberia_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    var nombreUsuario by mutableStateOf("Aldo Roldán")
        private set

    var telefono by mutableStateOf("+54 9 385 123-4567")
        private set

    var recordatoriosWhatsApp by mutableStateOf(true)
        private set

    val inicialUsuario: String
        get() = nombreUsuario.firstOrNull()?.uppercase() ?: "A"

    fun toggleRecordatorios(activo: Boolean) {
        recordatoriosWhatsApp = activo
    }
}