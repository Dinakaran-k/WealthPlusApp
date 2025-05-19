package com.dinakaran.wealthplusapp.domain.model

data class MutualFund(
    val name: String,
    val type: String,
    val currentValue: Double,
    val oneYearReturn: Double
)
