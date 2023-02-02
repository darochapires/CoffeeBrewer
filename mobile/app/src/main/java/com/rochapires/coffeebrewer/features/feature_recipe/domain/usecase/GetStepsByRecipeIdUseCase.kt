package com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase

import com.rochapires.coffeebrewer.features.common.Resource
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Step
import com.rochapires.coffeebrewer.features.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetStepsByRecipeIdUseCase(
    private val repository: RecipeRepository
) {

    operator fun invoke(
        recipeId: Int,
    ): Flow<Resource<List<Step>>> = flow {
        try {
            emit(Resource.Loading())
            val recipes = repository.getStepsByRecipeId(recipeId)
            emit(Resource.Success(recipes))
        }
        catch (e: HttpException) {
            println(e.message)
            emit(Resource.Error("An unexpected error occurred"))
        }
        catch (e: IOException) {
            println(e.message)
            emit(Resource.Error("An unexpected error occurred. Please, check your Internet connection"))
        }
    }
}