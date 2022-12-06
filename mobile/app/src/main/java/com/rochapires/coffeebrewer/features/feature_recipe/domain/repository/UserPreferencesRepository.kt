package com.rochapires.coffeebrewer.features.feature_recipe.domain.repository

interface UserPreferencesRepository {
    suspend fun getDefaultMethod(): String?
    suspend fun setDefaultMethod(methodId: String)

    suspend fun getDefaultRecipe(): String?
    suspend fun setDefaultRecipe(recipeId: String)

    suspend fun getDefaultCoffeeQuantity(): Int?
    suspend fun setDefaultCoffeeQuantity(coffeeQuantity: Int)
}