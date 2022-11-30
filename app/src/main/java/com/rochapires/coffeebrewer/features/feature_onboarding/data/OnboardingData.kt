package com.rochapires.coffeebrewer.features.feature_onboarding.data

import com.rochapires.coffeebrewer.features.feature_onboarding.domain.model.OnboardingItem

object OnboardingData {

    val onboardingItems = listOf(
        OnboardingItem("Home Brewer"),
        OnboardingItem("What is your favourite brewing method?"),
        OnboardingItem("What is your favourite brewing recipe?"),
        OnboardingItem("How much coffee do you use? (Ex: 15gr for 200ml of coffee)"),
    )

}