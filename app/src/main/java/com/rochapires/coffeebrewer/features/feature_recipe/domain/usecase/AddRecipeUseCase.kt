package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.InvalidRecipeException
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository

class AddRecipeUseCase(
    private val repository: RecipeRepository
) {

    @Throws(InvalidRecipeException::class)
    suspend operator fun invoke(recipe: Recipe) {

        if(recipe.name.isBlank()) {
            throw InvalidRecipeException("The name of the recipe can't be empty")
        }
        //TODO: add other fields

        return repository.insertRecipe(recipe)
    }
}