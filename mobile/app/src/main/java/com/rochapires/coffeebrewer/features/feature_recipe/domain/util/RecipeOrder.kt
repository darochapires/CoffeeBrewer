package com.rochapires.coffeebrewer.features.feature_recipe.domain.util

sealed class RecipeOrder(
    val orderType: OrderType
) {
    class Name(orderType: OrderType): RecipeOrder(orderType)
    class Date(orderType: OrderType): RecipeOrder(orderType)

    fun copy(orderType: OrderType): RecipeOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
        }
    }
}
