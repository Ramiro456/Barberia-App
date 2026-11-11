package com.example.barberia_app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UbicacionViewModel : ViewModel() {

    // Datos fijos de la sucursal
    val nombreSucursal = "DIRECCIÓN CENTRAL"
    val direccion = "Av. Belgrano Sur 1420"
    val zona = "Centro • Santiago del Estero"
    val telefonoWhatsApp = "+5493851234567" // Reemplazar por el número real

    // Estados observables
    var ubicacionUsuario by mutableStateOf<String?>(null)
        private set

    var permisoUbicacionDenegado by mutableStateOf(false)
        private set

    // Funciones para actualizar el estado desde la vista
    fun actualizarUbicacion(latitud: Double, longitud: Double) {
        ubicacionUsuario = "Lat: $latitud, Lon: $longitud"
        permisoUbicacionDenegado = false
    }

    fun onPermisoDenegado() {
        permisoUbicacionDenegado = true
        ubicacionUsuario = null
    }
}