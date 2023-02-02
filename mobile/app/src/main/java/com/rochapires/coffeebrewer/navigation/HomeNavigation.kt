package com.rochapires.coffeebrewer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.features.feature_home.presentation.HomeScreen
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.MethodsScreen
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail.RecipeDetailScreen
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.RecipesScreen
import com.rochapires.coffeebrewer.features.feature_settings.presentation.SettingsScreen

@Composable
fun HomeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screen.MethodsScreen.route) {
            MethodsScreen(navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }
        composable(route = Screen.RecipesScreen.route + "/{${Constants.METHOD_ID_KEY}}/{${Constants.METHOD_NAME_KEY}}") {
            RecipesScreen(navController)
        }
        composable(route = Screen.RecipeDetailScreen.route + "/{${Constants.RECIPE_ID_KEY}}") {
            RecipeDetailScreen(navController)
        }
    }
}