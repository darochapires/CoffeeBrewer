package com.rochapires.coffeebrewer.features.feature_recipe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Method(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    //val recipes: List<Recipe>
)
