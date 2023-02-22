package com.rochapires.coffeebrewer.features.feature_recipe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val coffee_amount: Double,
    val water_amount: Double,
    val water_temperature: Double,
    val time: Long,
    val grind_size: String,
    val timestamp: Long,
    val methodId: Int
)

class InvalidRecipeException(message: String): Exception(message)