package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.coffee_quantity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.common.Constants
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeQuantityViewModel @Inject constructor(
    private val recipesUseCase: RecipeUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun hasDefaultCoffeeQuantity() : Boolean {
        var coffeeQuantity = savedStateHandle.get<Double>(Constants.DEFAULT_COFFEE_QUANTITY_KEY)
        if(coffeeQuantity == null) {
            viewModelScope.launch {
                coffeeQuantity = recipesUseCase.getDefaultCoffeeQuantityUseCase()
            }
        }
        return  coffeeQuantity != null
    }

    fun onEvent(event: CoffeeQuantityEvent) {
        when (event) {
            is CoffeeQuantityEvent.DoneInserting -> {
                viewModelScope.launch {
                    recipesUseCase.saveDefaultCoffeeQuantityUseCase(event.quantity)
                }
            }
        }
    }
}