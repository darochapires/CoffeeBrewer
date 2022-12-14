package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method

data class MethodsState(
    val isLoading: Boolean = false,
    val methods: List<Method> = emptyList(),
    val error: String = ""
)
