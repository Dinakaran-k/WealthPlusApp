package com.dinakaran.wealthplusapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinakaran.wealthplusapp.presentation.ui.DashboardScreen
import com.dinakaran.wealthplusapp.presentation.ui.LoginOtpScreen
import com.dinakaran.wealthplusapp.presentation.viewmodel.DashboardViewModel
import com.dinakaran.wealthplusapp.presentation.viewmodel.LoginViewModel

@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    dashboardViewModel: DashboardViewModel
) {
    val navController = rememberNavController()
    val loginState = loginViewModel.state.collectAsState().value

    val startDestination = if (loginState.isLoggedIn) "dashboard" else "loginOtp"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("loginOtp") {
            LoginOtpScreen(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("loginOtp") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                dashboardViewModel = dashboardViewModel,
                onLogout = {
                    dashboardViewModel.logout()
                    loginViewModel.resetLoginState()
                    navController.navigate("loginOtp") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    }
}