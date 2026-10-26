package com.example.barberia_app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var nombre by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var telefono by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisibility by mutableStateOf(false)
        private set

    //funcion que no permite presionar el boton si los campos estan vacios
    val isRegisterEnable: Boolean
        get() = nombre.isNotBlank() &&
                email.isNotBlank() &&
                telefono.isNotBlank() &&
                password.isNotBlank()

    fun onNombreChange(nuevoNombre: String){
        nombre = nuevoNombre
    }

    fun onEmailChange(nuevoEmail: String){
        email = nuevoEmail
    }

    fun onTelefonoChange(nuevoTelefono: String){
        telefono = nuevoTelefono
    }

    fun onPasswordChange(nuevoPassword: String){
        password = nuevoPassword
    }

    fun togglePasswordVisibility(){ //invierte el valor del "ojo" de la contraseña
        isPasswordVisibility = !isPasswordVisibility
    }

    fun register(onSuccess: () -> Unit ){
        if(isRegisterEnable){
            onSuccess()
        }
    }
}