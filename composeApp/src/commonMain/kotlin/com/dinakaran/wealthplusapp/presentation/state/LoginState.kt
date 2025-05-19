package com.dinakaran.wealthplusapp.presentation.state

data class LoginState(
    val mobileNumber: String = "",
    val otp: String = "",
    val isVerifying: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val showOtpField: Boolean = false
)