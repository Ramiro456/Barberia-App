package com.example.barberia_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.barberia_app.screen.LoginScreen
import com.example.barberia_app.screen.RegisterScreen

sealed class Screen(val route: String){
    object Login: Screen("login") //cada pantalla es un objeto unico que hereda de screen
    object Register: Screen("register")
    object Home: Screen("home")
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController() //gestiona la pila de pantallas

    //NavHost area donde se intercambian las pantallas dinamicamente
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route //indica que pantalla debe mostrarse apenas abra la aplicacion
    ){
        composable(route = Screen.Login.route){
            LoginScreen(
                onLoginSucess = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(route = Screen.Register.route){
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.popBackStack() //quita la pantalla actual de la cima de la pila, es el "retroceder"
                },
                onGoogleSignInClick = {
                    //Integracion pendiente con Google
                }
            )
        }

        /*composable(route = Screen.Home.route){
            //falta el home de la barberia aca
        }*/
    }
}