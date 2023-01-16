package com.rochapires.coffeebrewer.features.feature_recipe.data.remote

import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface CoffeeApi {

    @GET("/methods")
    suspend fun getMethods(): List<Method>

    @GET("/methods/{id}")
    suspend fun getMethodById(@Path("id")id: Int): Method?

    @GET("/recipes/GetByMethod/{methodId}")
    suspend fun getRecipesByMethodId(@Path("methodId")methodId: Int): List<Recipe>

    @GET("/recipes/{id}")
    suspend fun getRecipeById(@Path("id")id: Int): Recipe?
}