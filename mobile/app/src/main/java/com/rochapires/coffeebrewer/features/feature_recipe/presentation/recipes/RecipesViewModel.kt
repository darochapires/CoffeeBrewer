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
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private var methodName: String

    init {
        methodName = savedStateHandle.get<String>(Constants.METHOD_NAME_KEY) ?: ""
        var methodId = savedStateHandle.get<String>(Constants.METHOD_ID_KEY)?.toInt()
        if(methodId == null) {
            viewModelScope.launch {
                methodId = recipesUseCase.getDefaultMethodUseCase()
            }
        }
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
            is RecipesEvent.ItemSelected -> {

            }
        }
    }

    private fun getRecipes(methodId: Int, recipeOrder: RecipeOrder) {
        recipesUseCase.getRecipesByMethodIdUseCase(methodId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RecipesState(
                        methodName = methodName,
                        recipes = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = RecipesState(
                        methodName = methodName,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RecipesState(
                        methodName = methodName,
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}