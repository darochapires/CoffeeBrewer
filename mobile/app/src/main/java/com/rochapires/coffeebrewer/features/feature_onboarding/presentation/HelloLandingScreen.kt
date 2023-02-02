package com.rochapires.coffeebrewer.features.feature_onboarding.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HelloLandingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Hello, there\n\n" +
                    "I will be helping you creating a better cup of coffee\n\n" +
                    "But first, I would like to ask you some questions",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp),
            text = "You can skip if you want to",
            style = MaterialTheme.typography.body1,
        )
    }
}