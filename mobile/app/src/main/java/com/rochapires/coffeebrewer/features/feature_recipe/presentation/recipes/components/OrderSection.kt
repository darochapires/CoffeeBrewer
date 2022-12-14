package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.OrderType
import com.rochapires.coffeebrewer.features.feature_recipe.domain.util.RecipeOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    recipeOrder: RecipeOrder = RecipeOrder.Date(OrderType.Descending),
    onOrderChange: (RecipeOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Name",
                selected = recipeOrder is RecipeOrder.Name,
                onSelect = { onOrderChange(RecipeOrder.Name(recipeOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DefaultRadioButton(
                    text = "Date",
                    selected = recipeOrder is RecipeOrder.Date,
                    onSelect = { onOrderChange(RecipeOrder.Date(recipeOrder.orderType)) }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = recipeOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange( recipeOrder.copy(OrderType.Ascending) ) }
            )

            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                DefaultRadioButton(
                    text = "Descending",
                    selected = recipeOrder.orderType is OrderType.Descending,
                    onSelect = { onOrderChange(RecipeOrder.Date(recipeOrder.orderType)) }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}