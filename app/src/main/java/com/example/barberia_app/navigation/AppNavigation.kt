package com.example.barberia_app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.barberia_app.components.Nav
import com.example.barberia_app.screen.Home_Catalogo
import com.example.barberia_app.screen.LoginScreen
import com.example.barberia_app.screen.RegisterScreen
import com.example.barberia_app.screen.ServicioModel
import com.example.barberia_app.screen.TurnosScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<BarberRoute>(BarberRoute.Login) }
    val currentRoute = backStack.lastOrNull()

    var serviciosSeleccionadosParaTurno by remember { mutableStateOf<List<ServicioModel>>(emptyList()) }
    var totalMontoParaTurno by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        }
    ) { route ->
        when (route) {
            is BarberRoute.Login -> NavEntry<BarberRoute>(key = route) {
                LoginScreen(
                    onLoginSucess = {
                        backStack.clear()
                        backStack.add(BarberRoute.Home)
                    },
                    onRegisterClick = {
                        backStack.add(BarberRoute.Register)
                    }
                )
            }

            is BarberRoute.Register -> NavEntry(key = route) {
                RegisterScreen(
                    onRegisterSuccess = {
                        backStack.clear()
                        backStack.add(BarberRoute.Home)
                    },
                    onLoginClick = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    onGoogleSignInClick = {
                        //para inicar sesion con google
                    }
                )
            }

            is BarberRoute.Home -> NavEntry(key = route) {
                Home_Catalogo(
                    onNavigateToTurnos = { servicios, total ->
                        serviciosSeleccionadosParaTurno = servicios
                        totalMontoParaTurno = total
                        backStack.add(BarberRoute.Turnos) }
                )
            }

            is BarberRoute.Turnos -> NavEntry<BarberRoute>(key = route) {
                TurnosScreen(
                    servicioSeleccionados = serviciosSeleccionadosParaTurno,
                    totalMonto = totalMontoParaTurno,
                    onBackClick = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                    onConfirmarClick = {
                        serviciosSeleccionadosParaTurno = emptyList()
                        totalMontoParaTurno = 0
                        backStack.clear()
                        backStack.add(BarberRoute.Home)
                    }
                )
            }
        }
    }


    if (currentRoute !is BarberRoute.Login && currentRoute !is BarberRoute.Register) {
        Nav(
            rutaActual = if (currentRoute is BarberRoute.Turnos) "turnos" else "home",
            onNavigateToHome = {
                if (currentRoute !is BarberRoute.Home) {
                    backStack.add(BarberRoute.Home)
                }
            },
            onNavigateToTurnos = {
                if (currentRoute !is BarberRoute.Turnos) {
                    backStack.add(BarberRoute.Turnos)
                }
            },
            onNavigateToUbicacion = { },
            onNavigateToPerfil = { },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
}
