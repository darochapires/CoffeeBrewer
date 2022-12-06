package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository

class GetRecipeUseCase(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke(id: String): Recipe? {
        return repository.getRecipeById(id)
    }
}