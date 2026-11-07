package com.example.barberia_app.navigation

import kotlinx.serialization.Serializable

sealed interface BarberRoute {
    @Serializable
    data object Login : BarberRoute

    @Serializable
    data object Register : BarberRoute

    @Serializable
    data object Home : BarberRoute

    @Serializable
    data object Turnos : BarberRoute
}