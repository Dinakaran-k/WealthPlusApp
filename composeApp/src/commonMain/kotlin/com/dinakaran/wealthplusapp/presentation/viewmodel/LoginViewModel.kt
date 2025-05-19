package com.dinakaran.wealthplusapp.presentation.viewmodel

import com.dinakaran.wealthplusapp.data.SessionDataSource
import com.dinakaran.wealthplusapp.presentation.state.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onMobileChange(mobile: String) {
        _state.value = _state.value.copy(mobileNumber = mobile)
    }

    fun onOtpChange(otp: String) {
        _state.value = _state.value.copy(otp = otp)
    }

    fun sendOtp() {
        _state.value = _state.value.copy(showOtpField = true, error = null)
    }

    fun login() {
        _state.value = _state.value.copy(isVerifying = true, error = null)
        CoroutineScope(Dispatchers.Default).launch {
            delay(1500)
            if (_state.value.otp == "123456") {
                SessionDataSource.saveSession(_state.value.mobileNumber)
                _state.value = _state.value.copy(isLoggedIn = true, isVerifying = false)
            } else {
                _state.value = _state.value.copy(error = "Invalid OTP", isVerifying = false)
            }
        }
    }

    fun resetLoginState() {
        _state.value = LoginState()
    }
}