package com.rochapires.coffeebrewer.features.feature_recipe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rochapires.coffeebrewer.util.Utils.formatDuration

@Entity
data class Step(
    @PrimaryKey
    val id: Int,
    val order: Int,
    val stepType: StepType,
    val title: String,
    val description: String?,
    val durationInSeconds: Long,
//    var durationInSecondsString: String = "${durationInSeconds.formatDuration()}",
    val recipeId: Int,
    val waterAmount: Int?,
//    var waterAmountString: String? = waterAmount?.let {"${it}g"}
)

class InvalidStepException(message: String): Exception(message)