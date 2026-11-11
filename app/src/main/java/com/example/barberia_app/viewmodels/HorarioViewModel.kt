package com.example.barberia_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HorarioViewModel : ViewModel() {

    data class HorarioModel(
        val dias: String,
        val horario: String,
        val destacado: Boolean = false
    )

    val listaHorarios = listOf(
        HorarioModel("Martes a Sábados", "09:00 - 21:00 hs", destacado = true),
        HorarioModel("Lunes", "14:00 - 20:00 hs"),
        HorarioModel("Domingos", "Cerrado")
    )

    var estaAbierto by mutableStateOf(true)
        private set

    val textoEstadoLocal: String
        get() = if (estaAbierto) "Abierto ahora" else "Cerrado"

    fun actualizarEstadoLocal(abierto: Boolean) {
        estaAbierto = abierto
    }
}