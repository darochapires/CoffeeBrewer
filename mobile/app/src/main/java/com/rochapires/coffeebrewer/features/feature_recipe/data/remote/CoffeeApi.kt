package com.rochapires.coffeebrewer.features.feature_recipe.data.remote

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeApi {

    @GET("/methods.json")
    suspend fun getMethods(): List<Method>

    @GET("/methods/{id}.json")
    suspend fun getMethodById(@Path("id")id: String): Method?

    @GET("/recipes/{methodId}.json")
    suspend fun getRecipesByMethodId(@Path("methodId")methodId: String): List<Recipe>

    @GET("/recipes/{id}.json")
    suspend fun getRecipeById(@Path("id")id: String): Recipe?
}