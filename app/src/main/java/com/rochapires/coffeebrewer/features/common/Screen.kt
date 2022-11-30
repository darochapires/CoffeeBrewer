package com.rochapires.coffeebrewer.features.common

sealed class Screen(val route: String) {
    object HelloLandingScreen: Screen("hello_landing_screen")
    object MethodsScreen: Screen("methods_screen")
}
