package com.dinakaran.wealthplusapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform