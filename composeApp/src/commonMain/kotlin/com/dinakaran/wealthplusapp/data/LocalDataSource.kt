package com.dinakaran.wealthplusapp.data

import com.dinakaran.wealthplusapp.domain.model.MutualFund
import kotlinx.coroutines.delay

object LocalDataSource {
    suspend fun getMutualFunds(): List<MutualFund> {
        delay(1500)
        return listOf(
            MutualFund("HDFC Mid-Cap", "Equity", 87500.0, +12.7),
            MutualFund("SBI Blue-chip", "Equity", 92000.0, +9.2),
            MutualFund("ICICI Bond", "Debt", 65000.0, -6.8),
            MutualFund("Axis Balanced", "Hybrid", 45000.0, +8.5),
            MutualFund("UTI Equity", "Equity", 68000.0, -10.2)
        )
    }
}