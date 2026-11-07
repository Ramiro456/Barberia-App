package com.example.barberia_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.barberia_app.model.DiaModel
import com.example.barberia_app.model.ProfesionalModel

class TurnosViewModel : ViewModel() {
    //ejemplos estatico

    val profesionales = listOf(
        ProfesionalModel("0", "Cualquiera", "Disponible", "★", esCualquiera = true),
        ProfesionalModel("1", "Marcos", "Barbero", "M"),
        ProfesionalModel("2", "Lucas", "Barbero", "L")
    )
    val diasDisponibles = listOf(
        DiaModel("LUN", "19", "Lun 19"),
        DiaModel("MAR", "20", "Mar 20"),
        DiaModel("MIÉ", "21", "Mié 21"),
        DiaModel("JUE", "22", "Jue 22"),
        DiaModel("VIE", "23", "Vie 23"),
        DiaModel("SÁB", "24", "Sáb 24")
    )

    val horariosDisponibles = listOf(
        "09:30" to true,
        "10:30" to true,
        "11:45" to true,
        "12:30" to false, // Ocupado
        "16:00" to true,
        "17:15" to true,
        "18:30" to true,
        "19:45" to true
    )

    var profesionalSelecionadoId by mutableStateOf("0")
        private set
    var diaSeleccionado by mutableStateOf(diasDisponibles[1]) //Martes 20 por defecto
        private set

    var horaSeleccionada by mutableStateOf<String?>("10:30")
        private set

    fun onProfesionalSelected(id: String){
        profesionalSelecionadoId = id
    }

    fun onDiaSelected(dia: DiaModel){
        diaSeleccionado = dia
    }

    fun onHoraSelected(hora: String){
        horaSeleccionada = hora
    }

}