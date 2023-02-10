package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.common.Resource
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipesUseCase: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(RecipeDetailState())
    val state: State<RecipeDetailState> = _state

    init {
        var recipeId: Int = -1
        val recipeIdString = savedStateHandle.get<String>(Constants.RECIPE_ID_KEY)
        if(recipeIdString.isNullOrBlank()) {
            viewModelScope.launch {
                recipesUseCase.getDefaultRecipeUseCase()?.let {
                    recipeId = recipesUseCase.getDefaultRecipeUseCase()!!
                }
            }
        } else {
            recipeId = recipeIdString.toInt()
        }

        //TODO wait for both calls
        getRecipe(recipeId)
        getSteps(recipeId)
    }

    private fun getRecipe(recipeId: Int) {
        recipesUseCase.getRecipeUseCase(recipeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(recipe = result.data)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSteps(recipeId: Int) {
        recipesUseCase.getStepsByRecipeIdUseCase(recipeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        steps = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.StartRecipePressed -> {
                val step = _state.value.steps[0]
                _state.value = _state.value.copy(
                    brewTimerIsVisible = true,
                    brewTimerIsRunning = true,
                    brewTimerTotalTime = step.durationInSeconds,
                    currentStepIndex = 0
                )
            }
            is RecipeDetailEvent.StepFinished -> {
                if(_state.value.currentStepIndex == _state.value.steps.size - 1) {
                    //brew finished
                    //TODO brew finished
                    _state.value = _state.value.copy(
                        brewTimerIsRunning = false,
                        brewTimerIsVisible = false,
                        currentStepIndex = null
                    )
                    return
                }
                val currentStepIndex = if(_state.value.currentStepIndex != null) _state.value.currentStepIndex!! + 1 else 0
                val step = _state.value.steps[currentStepIndex]
                _state.value = _state.value.copy(
                    brewTimerTotalTime = step.durationInSeconds,
                    currentStepIndex = currentStepIndex
                )
            }
            is RecipeDetailEvent.SetAsDefaultPressed -> {
                viewModelScope.launch {
                    state.value.recipe?.let { recipesUseCase.saveDefaultRecipeUseCase(it.id) }
                }
            }
            is RecipeDetailEvent.LikePressed -> {
                viewModelScope.launch {
                    //TODO
                }
            }
        }
    }

}