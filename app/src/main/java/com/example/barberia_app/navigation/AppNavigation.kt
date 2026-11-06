package com.example.barberia_app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.barberia_app.components.Nav
import com.example.barberia_app.screen.Home_Catalogo
import com.example.barberia_app.screen.LoginScreen
import com.example.barberia_app.screen.RegisterScreen

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<BarberRoute>(BarberRoute.Login) }
    val currentRoute = backStack.lastOrNull()
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
                    onNavigateToTurnos = { }
                )
            }
        }
    }

    if (currentRoute !is BarberRoute.Login && currentRoute !is BarberRoute.Register) {
        Nav(
            rutaActual = "Home",
            onNavigateToHome = {
                if (currentRoute !is BarberRoute.Home) {
                    backStack.add(BarberRoute.Home)
                }
            },
            onNavigateToTurnos = { /* backStack.add(BarberRoute.Turnos) */ },
            onNavigateToUbicacion = { /* backStack.add(BarberRoute.Ubicacion) */ },
            onNavigateToPerfil = { /* backStack.add(BarberRoute.Perfil) */ },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
}
