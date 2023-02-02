package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.coffee_quantity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.feature_onboarding.domain.usecase.OnboardingUseCases
import com.rochapires.coffeebrewer.features.feature_recipe.domain.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeQuantityViewModel @Inject constructor(
    private val recipesUseCase: RecipeUseCases,
    private val onboardingUseCases: OnboardingUseCases
) : ViewModel() {
    private val _state = mutableStateOf(CoffeeQuantityState())
    val state: State<CoffeeQuantityState> = _state

    fun onEvent(event: CoffeeQuantityEvent) {
        when (event) {
            is CoffeeQuantityEvent.DoneInserting -> {
                viewModelScope.launch {
                    recipesUseCase.saveDefaultCoffeeQuantityUseCase(event.quantity)
                    _state.value = CoffeeQuantityState(
                        coffeeQuantity = event.quantity
                    )
                }
            }
            is CoffeeQuantityEvent.GetStartedButtonClicked -> {
                viewModelScope.launch {
                    onboardingUseCases.saveOnboardingDoneUseCase(event.done)
                }
            }
        }
    }
}