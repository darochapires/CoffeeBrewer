package com.rochapires.coffeebrewer.features.feature_onboarding.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository

class SaveOnboardingDoneUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(done: Boolean)  {
        repository.setOnboardingDone(done = done)
    }
}