package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Step

data class RecipeDetailState (
    val isLoading: Boolean = false,
    val error: String = "",
    val recipe: Recipe? = null,
    val steps: List<Step> = emptyList(),
    val brewTimerIsVisible: Boolean = false,

    //timer
    val brewTimerIsRunning: Boolean = false,

    /**
     * time in seconds
     */
    val brewTimerTotalTime: Long = 0L,
    val currentStepIndex: Int = -1
)
