package com.rochapires.coffeebrewer.features.feature_recipe.domain.model

import com.google.gson.annotations.SerializedName

enum class StepType(val value: Int) {
    @SerializedName("0")
    Whole(0),
    @SerializedName("1")
    Pour(1),
    @SerializedName("2")
    Wait(2),
    @SerializedName("3")
    Stir(3),
    @SerializedName("4")
    Swirl(4),
    @SerializedName("5")
    Bloom(5)
}