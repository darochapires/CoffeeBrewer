package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PagerItem(pageNumber: Int) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "page: $pageNumber")
    }
}