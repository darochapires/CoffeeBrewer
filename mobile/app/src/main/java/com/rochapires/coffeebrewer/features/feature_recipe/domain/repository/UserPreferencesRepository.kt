package com.rochapires.coffeebrewer.features.feature_recipe.domain.repository

interface UserPreferencesRepository {
    suspend fun getDefaultMethod(): Int?
    suspend fun setDefaultMethod(methodId: Int)

    /*suspend fun getDefaultRecipe(): Int?
    suspend fun setDefaultRecipe(recipeId: Int)*/

    suspend fun getDefaultCoffeeQuantity(): Double?
    suspend fun setDefaultCoffeeQuantity(coffeeQuantity: Double)
}