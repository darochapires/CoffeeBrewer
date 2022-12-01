package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository

class SaveDefaultMethodUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(methodId: String)  {
        repository.setDefaultMethod(methodId = methodId)
    }
}