package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe

@Composable
fun RecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onItemClick: (Recipe) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(recipe) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

    }
}