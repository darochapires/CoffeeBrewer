package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method

sealed class MethodsEvent {
    data class ItemSelected(val method: Method, val isLanding: Boolean = false): MethodsEvent()
}
