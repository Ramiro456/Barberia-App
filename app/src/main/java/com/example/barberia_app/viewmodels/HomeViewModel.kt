package com.example.barberia_app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.barberia_app.screen.ServicioModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class HomeViewModel : ViewModel() {
    val listaServicios = listOf(
        ServicioModel("1", "Corte Fade / Degradé", "45 min • Tijera y máquina", "$10.000", "Cortes"),
        ServicioModel("2", "Corte Tradicional", "35 min • Solo tijera o máquina", "$7.500", "Cortes"),
        ServicioModel("3", "Perfilado de Barba", "30 min • Navaja", "$5.000", "Barba")
    )

    var categoriaSeleccionada by mutableStateOf("Todos")
        private set

    var serviciosSeleccionadosIds by mutableStateOf(setOf<String>())
        private set

    val serviciosFiltrados: List<ServicioModel>
        get() = if (categoriaSeleccionada == "Todos") {
            listaServicios
        } else {
            listaServicios.filter { it.categoria == categoriaSeleccionada }
        }

    val servicioSeleccionados: List<ServicioModel>
        get() = listaServicios.filter { serviciosSeleccionadosIds.contains(it.id) }

    val totalMonto: Int
        get() = servicioSeleccionados.sumOf { servicio ->
            servicio.precio.replace("[^0-9]".toRegex(),"").toIntOrNull() ?: 0
        }

    fun onCategoriaSelected(categoria: String){
        categoriaSeleccionada = categoria
    }

    fun toggleSeleccionServicio(servicioId: String){
        serviciosSeleccionadosIds = if (serviciosSeleccionadosIds.contains(servicioId)) {
            serviciosSeleccionadosIds - servicioId
        } else {
            serviciosSeleccionadosIds + servicioId
        }
    }
}