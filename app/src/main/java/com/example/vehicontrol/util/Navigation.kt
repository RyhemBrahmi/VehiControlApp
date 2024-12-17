package com.example.vehicontrol.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vehicontrol.presentation.CarburantScreen
import com.example.vehicontrol.presentation.HomeScreen
import com.example.vehicontrol.presentation.LoginPage
import com.example.vehicontrol.presentation.NettoyageScreen
import com.example.vehicontrol.presentation.SuiviQuotidienScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.MainScreen.route,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(ScreenRoutes.LoginPage.route) {
                LoginPage(
                    onNavigateToHomeScreen = {
                    navController.navigate(ScreenRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                    }
                )

            }
            composable(ScreenRoutes.MainScreen.route) {
                MainScreen(navController = navController)
            }

            composable(ScreenRoutes.CarburantScreen.route) {
                CarburantScreen(
                    onNavigateToHomeScreen = {
                        navController.navigate(ScreenRoutes.HomeScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable(ScreenRoutes.NettoyageScreen.route) {
                NettoyageScreen(
                    onNavigateToHomeScreen = {
                        navController.navigate(ScreenRoutes.HomeScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }

            composable(ScreenRoutes.SuiviQuotidienScreen.route) {
                SuiviQuotidienScreen(
                    onNavigateToHomeScreen = {
                        navController.navigate(ScreenRoutes.HomeScreen.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
            // Add remaining routes
            composable(ScreenRoutes.HomeScreen.route) {
                HomeScreen(
                    onNavigateToSuiviQuotidienScreen = {
                        navController.navigate(ScreenRoutes.SuiviQuotidienScreen.route) {
                            popUpTo(0)
                        }
                    },
                    onNavigateToNettoyageScreen = {
                        navController.navigate(ScreenRoutes.NettoyageScreen.route) {
                            popUpTo(0)
                        }
                    },
                    onNavigateToCarburantScreen = {
                        navController.navigate(ScreenRoutes.CarburantScreen.route) {
                            popUpTo(0)
                        }
                    },
                )
            }
        }
    }
}

sealed class ScreenRoutes(val route: String) {
    object LoginPage : ScreenRoutes("loginPage")
    object CarburantScreen : ScreenRoutes("carburant_screen")
    object NettoyageScreen : ScreenRoutes("nettoyage_screen")
    object SuiviQuotidienScreen : ScreenRoutes("suivi_screen")
    object MainScreen : ScreenRoutes("main_screen")
    object HomeScreen : ScreenRoutes("home_screen")

}
