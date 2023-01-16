package com.rochapires.coffeebrewer.features.feature_recipe.data.repository

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.data.remote.CoffeeApi
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: CoffeeApi
    ) : RecipeRepository {

    override suspend fun insertRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun getMethods(): List<Method> {
        return api.getMethods()
    }

    override suspend fun getMethodById(id: Int): Method? {
        return api.getMethodById(id)
    }

    override suspend fun getRecipesByMethodId(id: Int): List<Recipe> {
        return api.getRecipesByMethodId(id)
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return api.getRecipeById(id)
    }
}