package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.common.Resource
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MethodsViewModel @Inject constructor(
    private val recipesUseCase: RecipeUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MethodsState())
    val state: State<MethodsState> = _state

    init {
        getMethods()
    }

    private fun getMethods() {
        recipesUseCase.getMethodsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MethodsState(
                        methods = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = MethodsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MethodsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MethodsEvent) {
        when (event) {
            is MethodsEvent.ItemSelected -> {
                viewModelScope.launch {
                    recipesUseCase.saveDefaultMethodUseCase(event.method.id)
                }
            }
        }
    }
}