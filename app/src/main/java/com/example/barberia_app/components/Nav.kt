package com.example.barberia_app.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.surfaseCard
import com.example.barberia_app.ui.theme.textMuted

@Composable
fun Nav(
    modifier: Modifier = Modifier,
    rutaActual: String = "home",
    onNavigateToHome: () -> Unit = {},
    onNavigateToTurnos: () -> Unit = {},
    onNavigateToUbicacion: () -> Unit = {},
    onNavigateToPerfil: () -> Unit = {}
) {

    NavigationBar(
        modifier = modifier,
        containerColor = surfaseCard,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = rutaActual == "home",
            onClick = onNavigateToHome,
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = goldPrimary,
                selectedTextColor = goldPrimary,
                unselectedIconColor = textMuted,
                unselectedTextColor = textMuted,
                indicatorColor = surfaseCard
            )
        )
        NavigationBarItem(
            selected = rutaActual == "turnos",
            onClick = onNavigateToTurnos,
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Turnos") },
            label = { Text("Turnos", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = goldPrimary,
                selectedTextColor = goldPrimary,
                unselectedIconColor = textMuted,
                unselectedTextColor = textMuted,
                indicatorColor = surfaseCard
            )
        )
        NavigationBarItem(
            selected = rutaActual == "ubicacion",
            onClick = onNavigateToUbicacion,
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Ubicación") },
            label = { Text("Ubicación", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = goldPrimary,
                selectedTextColor = goldPrimary,
                unselectedIconColor = textMuted,
                unselectedTextColor = textMuted,
                indicatorColor = surfaseCard
            )
        )
        NavigationBarItem(
            selected = rutaActual == "perfil",
            onClick = onNavigateToPerfil,
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = goldPrimary,
                selectedTextColor = goldPrimary,
                unselectedIconColor = textMuted,
                unselectedTextColor = textMuted,
                indicatorColor = surfaseCard
            )
        )
    }
}