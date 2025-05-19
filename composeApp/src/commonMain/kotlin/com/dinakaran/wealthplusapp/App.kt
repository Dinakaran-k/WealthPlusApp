package com.dinakaran.wealthplusapp

import androidx.compose.runtime.Composable
import com.dinakaran.wealthplusapp.presentation.navigation.AppNavigation
import com.dinakaran.wealthplusapp.presentation.viewmodel.DashboardViewModel
import com.dinakaran.wealthplusapp.presentation.viewmodel.LoginViewModel

@Composable
fun App() {

    val loginViewModel = LoginViewModel()
    val dashboardViewModel = DashboardViewModel()
    AppNavigation(loginViewModel, dashboardViewModel)

}