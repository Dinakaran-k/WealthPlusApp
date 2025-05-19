package com.dinakaran.wealthplusapp.presentation.state

import com.dinakaran.wealthplusapp.domain.model.MutualFund

data class DashboardState(
    val isLoading: Boolean = false,
    val mutualFunds: List<MutualFund> = emptyList(),
    val totalValue: String = "",
    val filter: String = "All Funds"
)