package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository

class SaveDefaultRecipeUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(recipeId: Int)  {
        repository.setDefaultRecipe(recipeId = recipeId)
    }
}