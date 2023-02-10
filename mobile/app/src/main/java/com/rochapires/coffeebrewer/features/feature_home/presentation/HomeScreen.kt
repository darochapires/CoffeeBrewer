package com.rochapires.coffeebrewer.features.feature_home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail.RecipeDetailScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if(state.recipeId != null) {
        RecipeDetailScreen()
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "You can select a default recipe that will appear on this screen every time you open the app",
                textAlign = TextAlign.Center
            )
        }
    }
}