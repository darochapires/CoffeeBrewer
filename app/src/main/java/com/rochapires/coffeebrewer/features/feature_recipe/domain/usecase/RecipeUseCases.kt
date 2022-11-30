package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

data class RecipeUseCases (
    //val getRecipesUseCase: GetRecipesUseCase,
    val getMethodsUseCase: GetMethodsUseCase,
    val getMethodUseCase: GetMethodUseCase,
    val getRecipesByMethodIdUseCase: GetRecipesByMethodIdUseCase,
    val getRecipeUseCase: GetRecipeUseCase,
    val addRecipeUseCase: AddRecipeUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase
)