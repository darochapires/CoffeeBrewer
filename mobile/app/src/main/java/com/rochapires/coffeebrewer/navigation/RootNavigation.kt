package com.rochapires.coffeebrewer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.features.feature_home.presentation.BottomBarScaffold
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.pager.PagerScreen

@Composable
fun RootNavigation(onboardingDone: Boolean) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if(onboardingDone) Screen.HomeScreen.route else  Screen.HelloLandingScreen.route
    ) {
        composable(route = Screen.HelloLandingScreen.route) {
            PagerScreen(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            BottomBarScaffold()
        }
    }
}