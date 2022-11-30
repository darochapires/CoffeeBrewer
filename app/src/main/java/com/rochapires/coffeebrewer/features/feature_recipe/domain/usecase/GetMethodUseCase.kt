package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository

class GetMethodUseCase(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke(id: Int): Method? {
        return repository.getMethodById(id)
    }
}