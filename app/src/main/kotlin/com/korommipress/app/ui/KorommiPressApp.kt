package com.korommipress.app.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.korommipress.app.ui.navigation.NavigationRoute
import com.korommipress.app.ui.screens.auth.AuthScreen
import com.korommipress.app.ui.screens.home.HomeScreen
import com.korommipress.app.ui.screens.splash.SplashScreen
import com.korommipress.app.viewmodel.AuthViewModel

@Composable
fun KorommiPressApp() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SPLASH
    ) {
        composable(NavigationRoute.SPLASH) {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate(NavigationRoute.AUTH) {
                        popUpTo(NavigationRoute.SPLASH) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(NavigationRoute.HOME) {
                        popUpTo(NavigationRoute.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoute.AUTH) {
            AuthScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(NavigationRoute.HOME) {
                        popUpTo(NavigationRoute.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoute.HOME) {
            HomeScreen(
                onLogout = {
                    navController.navigate(NavigationRoute.AUTH) {
                        popUpTo(NavigationRoute.HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}
