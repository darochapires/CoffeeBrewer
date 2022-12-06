package com.rochapires.coffeebrewer.features.feature_onboarding.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.MethodsScreen

@Composable
fun MethodsLandingScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MethodsScreen()
    }
}