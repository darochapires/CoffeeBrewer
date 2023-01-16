package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository

class SaveDefaultCoffeeQuantityUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(coffeeQuantity: Double)  {
        repository.setDefaultCoffeeQuantity(coffeeQuantity = coffeeQuantity)
    }
}