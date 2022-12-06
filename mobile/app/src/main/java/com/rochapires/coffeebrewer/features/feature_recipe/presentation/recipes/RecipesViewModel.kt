package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.common.Resource
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.RecipeUseCases
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.OrderType
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.RecipeOrder
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.MethodsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesUseCase: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(RecipesState())
    val state: State<RecipesState> = _state

    private var lastDeletedRecipe: Recipe? = null

    //private var getRecipesJob: Job? = null

    init {
        var methodId = savedStateHandle.get<String>(Constants.METHOD_ID)
        if(methodId == null) {
            viewModelScope.launch {
                methodId = recipesUseCase.getDefaultMethodUseCase()
            }
        }
        //savedStateHandle.get<Int>(Constants.METHOD_ID)?.let { methodId ->
        //    getRecipes(methodId, RecipeOrder.Date(OrderType.Descending))
        //}
        methodId?.let {
            getRecipes(it, RecipeOrder.Date(OrderType.Descending))
        }
    }

    fun onEvent(event: RecipesEvent) {
        when (event) {
            is RecipesEvent.Order -> {
                if (event.recipeOrder::class == state.value.recipeOrder::class &&
                    event.recipeOrder.orderType == state.value.recipeOrder.orderType
                ) {
                    return
                }

            }
            is RecipesEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    recipesUseCase.deleteRecipeUseCase(event.recipe)
                    lastDeletedRecipe = event.recipe
                }
            }
            is RecipesEvent.RestoreRecipe -> {
                viewModelScope.launch {
                    recipesUseCase.addRecipeUseCase(lastDeletedRecipe ?: return@launch)
                    lastDeletedRecipe = null
                }
            }
            is RecipesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    //
//    private fun getRecipes(recipeOrder: RecipeOrder) {
//        getRecipesJob?.cancel()
//        getRecipesJob = recipesUseCase.getRecipesUseCase(recipeOrder)
//            .onEach { recipes ->
//                _state.value = state.value.copy(
//                    recipes = recipes,
//                    recipeOrder = recipeOrder
//                )
//            }
//            .launchIn(viewModelScope)
//    }

    private fun getRecipes(methodId: String, recipeOrder: RecipeOrder) {
        recipesUseCase.getRecipesByMethodIdUseCase(methodId).onEach { recipes ->
            when (recipes) {
                is Resource.Success -> {
                    _state.value = RecipesState(
                        recipes = recipes.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = RecipesState(
                        error = recipes.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RecipesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}