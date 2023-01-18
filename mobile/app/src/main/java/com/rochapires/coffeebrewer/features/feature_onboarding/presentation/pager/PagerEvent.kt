package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.pager

sealed class PagerEvent {
    data class SkipButtonClicked(val done: Boolean = true): PagerEvent()
}
