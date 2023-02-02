package com.rochapires.coffeebrewer.features.common

sealed class Screen(val route: String) {
    object HelloLandingScreen: Screen("hello_landing_screen")
    object HomeScreen: Screen("home_screen")
    object SettingsScreen: Screen("settings_screen")
    object MethodsScreen: Screen("methods_screen")
    object RecipesScreen: Screen("recipes_screen")
    object RecipeDetailScreen: Screen("recipe_detail_screen")
}
