package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase
/*
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.OrderType
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.RecipeOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {

    operator fun invoke(
        recipeOrder: RecipeOrder = RecipeOrder.Name(OrderType.Ascending)
    ): Flow<List<Recipe>> {
        return repository.getRecipes().map { recipes ->
            when(recipeOrder.orderType) {
                is OrderType.Ascending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Name -> recipes.sortedBy { it.name }
                        is RecipeOrder.Date -> recipes.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(recipeOrder) {
                        is RecipeOrder.Name -> recipes.sortedByDescending { it.name }
                        is RecipeOrder.Date -> recipes.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}*/