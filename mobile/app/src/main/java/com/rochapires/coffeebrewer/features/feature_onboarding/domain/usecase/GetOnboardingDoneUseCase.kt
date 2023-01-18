package com.rochapires.coffeebrewer.features.feature_onboarding.domain.usecase

import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.UserPreferencesRepository

class GetOnboardingDoneUseCase(
    private val repository: UserPreferencesRepository
) {
    suspend operator fun invoke(): Boolean   {
        return repository.getOnboardingDone()
    }
}