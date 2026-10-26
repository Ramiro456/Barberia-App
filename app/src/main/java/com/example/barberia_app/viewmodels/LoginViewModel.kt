package com.example.barberia_app.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class LoginViewModel : ViewModel() {//LoginViewModel hereda de ViewModel
    var email by mutableStateOf("") //estado donde se va a guardar el correo
        private set //con esto solo el ViewModel puede modificarlo, si la pantalla quiere modificarlo debe llamar a onEmailChange()
    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false) //indica si la contraseña se va a mostrar o no
        private set

    val isLoginEnabled: Boolean //funcion para devolver los valores si no están vacíos
        get() = email.isNotBlank() && password.isNotBlank()

    fun onEmailChange(newEmail: String){ //funcion puente entre la pantalla y el ViewModel, sirve para cambiar el contenido de la variable
        email = newEmail
    }

    fun onPasswordChange(newPassword: String){
        password = newPassword
    }

    fun togglePasswordVisible(){ //invierte el valor del "ojo" de la contraseña
        isPasswordVisible = !isPasswordVisible
    }

    fun login(onSucces: () -> Unit){ //funcion que se va a ejecutar cuando se presione el boton de iniciar sesion
        if(isLoginEnabled){
            //
            onSucces()
        }
    }
}