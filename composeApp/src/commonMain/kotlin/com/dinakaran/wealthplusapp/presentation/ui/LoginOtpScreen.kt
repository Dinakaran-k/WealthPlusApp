package com.dinakaran.wealthplusapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinakaran.wealthplusapp.presentation.viewmodel.LoginViewModel

@Composable
fun LoginOtpScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val state by loginViewModel.state.collectAsState()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) onLoginSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WealthPlus",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3B82F6)
        )

        Spacer(Modifier.height(10.dp))
        Text(
            text = "Your Mutual Fund Portfolio Manager",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.mobileNumber,
            onValueChange = { mobile ->
                if (mobile.all { it.isDigit() } && mobile.length <= 10) {
                    loginViewModel.onMobileChange(mobile)
                }
            },
            label = { Text("Mobile Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            enabled = !state.showOtpField,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        if (!state.showOtpField) {
            Button(
                onClick = loginViewModel::sendOtp,
                modifier = Modifier.fillMaxWidth(),
                enabled = state.mobileNumber.length == 10
            ) {
                Text("Get OTP")
            }
        } else {
            OutlinedTextField(
                value = state.otp,
                onValueChange = { otp ->
                    if (otp.all { it.isDigit() } && otp.length <= 6) {
                        loginViewModel.onOtpChange(otp)
                    }
                },
                label = { Text("Enter 6-digit OTP") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Use OTP: 123456 (for demo)",
                fontSize = 12.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            if (state.error != null) {
                Text(state.error ?: "", color = Color.Red)
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = loginViewModel::login,
                modifier = Modifier.fillMaxWidth(),
                enabled = state.otp.length == 6 && !state.isVerifying
            ) {
                Text(if (state.isVerifying) "Verifying..." else "Login")
            }
        }
    }
}
