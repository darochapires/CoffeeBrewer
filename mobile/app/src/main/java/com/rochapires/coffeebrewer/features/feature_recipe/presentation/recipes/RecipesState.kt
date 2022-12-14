package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.OrderType
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.RecipeOrder

data class RecipesState (
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val recipeOrder: RecipeOrder = RecipeOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val error: String = ""
)
