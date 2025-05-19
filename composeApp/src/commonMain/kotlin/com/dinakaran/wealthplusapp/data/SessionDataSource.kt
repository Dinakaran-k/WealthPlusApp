package com.dinakaran.wealthplusapp.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

object SessionDataSource {
    private val settings = Settings()

    fun saveSession(mobileNumber: String) {
        settings["isLoggedIn"] = true
        settings["mobileNumber"] = mobileNumber
    }

    fun clearSession() = settings.clear()
}