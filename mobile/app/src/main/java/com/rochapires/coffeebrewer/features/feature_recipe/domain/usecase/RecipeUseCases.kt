package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

data class RecipeUseCases (
    val getMethodsUseCase: GetMethodsUseCase,
    val getMethodUseCase: GetMethodUseCase,
    val getRecipesByMethodIdUseCase: GetRecipesByMethodIdUseCase,
    val getRecipeUseCase: GetRecipeUseCase,
    val addRecipeUseCase: AddRecipeUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase,
    val getStepsByRecipeIdUseCase: GetStepsByRecipeIdUseCase,

    //defaults
    val saveDefaultMethodUseCase: SaveDefaultMethodUseCase,
    val getDefaultMethodUseCase: GetDefaultMethodUseCase,
    val saveDefaultRecipeUseCase: SaveDefaultRecipeUseCase,
    val getDefaultRecipeUseCase: GetDefaultRecipeUseCase,
    val saveDefaultCoffeeQuantityUseCase: SaveDefaultCoffeeQuantityUseCase,
    val getDefaultCoffeeQuantityUseCase: GetDefaultCoffeeQuantityUseCase
)