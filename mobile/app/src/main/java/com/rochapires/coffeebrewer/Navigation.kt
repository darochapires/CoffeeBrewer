package com.rochapires.coffeebrewer

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.pager.PagerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HelloLandingScreen.route
    ) {
        composable(route = Screen.HelloLandingScreen.route) {
            PagerScreen(navController)
        }
    }
}