package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.pager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rochapires.coffeebrewer.features.feature_onboarding.domain.usecase.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(
    private val onboardingUseCases: OnboardingUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun onEvent(event: PagerEvent) {
        when (event) {
            is PagerEvent.SkipButtonClicked -> {
                viewModelScope.launch {
                    onboardingUseCases.saveOnboardingDoneUseCase(event.done)
                }
            }
        }
    }
}