package com.dinakaran.wealthplusapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dinakaran.wealthplusapp.presentation.state.DashboardState
import com.dinakaran.wealthplusapp.presentation.viewmodel.DashboardViewModel


@Composable
fun FilterDropdownMenu(state: DashboardState, viewModel: DashboardViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(state.filter)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("All Funds", "Equity", "Debt", "Hybrid").forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        viewModel.setFilter(it)
                        expanded = false
                    }
                )
            }
        }
    }
}