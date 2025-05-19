package com.dinakaran.wealthplusapp.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinakaran.wealthplusapp.presentation.viewmodel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
    onLogout: () -> Unit
) {
    val state by dashboardViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "WealthPlus") },
                actions = {
                    OutlinedButton(
                        onClick = onLogout,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Gray),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            //  Portfolio Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Total Portfolio Value",
                        color = Color.Blue,
                        fontSize = 20.sp
                    )
                    Text(
                        text = state.totalValue,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Title & Filter Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Funds",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                FilterDropdownMenu(state, dashboardViewModel)
            }


            Spacer(Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator()
            } else {

                val filteredFunds = when (state.filter) {
                    "Equity" -> state.mutualFunds.filter { it.type == "Equity" }
                    "Debt" -> state.mutualFunds.filter { it.type == "Debt" }
                    "Hybrid" -> state.mutualFunds.filter { it.type == "Hybrid" }
                    else -> state.mutualFunds
                }

                LazyColumn {
                    items(filteredFunds.size) { index ->
                        val funds = filteredFunds[index]
                        FundCard(funds)
                    }
                }
            }
        }
    }
}


