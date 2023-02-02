package com.rochapires.coffeebrewer.features.feature_recipe.domain.repository

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Step

interface RecipeRepository {
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun getMethods(): List<Method>
    suspend fun getMethodById(id: Int): Method?
    suspend fun getRecipesByMethodId(id: Int): List<Recipe>
    suspend fun getRecipeById(id: Int): Recipe
    suspend fun getStepsByRecipeId(id: Int): List<Step>
}