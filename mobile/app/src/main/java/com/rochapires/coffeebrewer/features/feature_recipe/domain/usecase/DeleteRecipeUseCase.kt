package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository

class DeleteRecipeUseCase(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke(recipe: Recipe) {
        return repository.deleteRecipe(recipe)
    }
}