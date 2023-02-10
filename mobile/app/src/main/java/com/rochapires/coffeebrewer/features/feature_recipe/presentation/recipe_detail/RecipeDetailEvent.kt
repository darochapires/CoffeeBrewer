package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail

sealed class RecipeDetailEvent {
    object StartRecipePressed: RecipeDetailEvent()
    object StepFinished: RecipeDetailEvent()
    object SetAsDefaultPressed: RecipeDetailEvent()
    object LikePressed: RecipeDetailEvent()
}
