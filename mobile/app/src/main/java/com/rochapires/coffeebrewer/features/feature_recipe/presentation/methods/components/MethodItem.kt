package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.components

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

@Composable
fun MethodItem(
    method: Method,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onItemClick: (Method) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(method) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = method.name,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

    }
}