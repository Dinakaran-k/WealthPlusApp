package com.dinakaran.wealthplusapp.presentation.viewmodel

import com.dinakaran.wealthplusapp.data.LocalDataSource
import com.dinakaran.wealthplusapp.data.SessionDataSource
import com.dinakaran.wealthplusapp.presentation.state.DashboardState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel {
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state

    init {
        loadFunds()
    }

    fun loadFunds() {
        CoroutineScope(Dispatchers.Default).launch {
            _state.emit(_state.value.copy(isLoading = true))
            val funds = LocalDataSource.getMutualFunds()
            _state.emit(_state.value.copy(
                isLoading = false,
                mutualFunds = funds,
                totalValue = funds.sumOf { it.currentValue.toInt() }.toString()
            ))
        }
    }

    fun setFilter(filter: String) {
        _state.value = _state.value.copy(filter = filter)
    }

    fun logout() {
        SessionDataSource.clearSession()
    }
}