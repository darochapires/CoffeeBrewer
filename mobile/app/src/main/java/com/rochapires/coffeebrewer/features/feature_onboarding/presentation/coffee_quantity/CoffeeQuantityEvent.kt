package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.coffee_quantity

sealed class CoffeeQuantityEvent {
    data class DoneInserting(val quantity: Double): CoffeeQuantityEvent()
    data class GetStartedButtonClicked(val done: Boolean = true): CoffeeQuantityEvent()
}
