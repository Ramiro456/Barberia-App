package com.example.barberia_app.model

data class ProfesionalModel(
    val id: String,
    val nombre: String,
    val rol: String,
    val inicial: String,
    val esCualquiera: Boolean = false
)