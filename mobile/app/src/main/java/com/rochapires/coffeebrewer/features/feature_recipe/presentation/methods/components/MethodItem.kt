package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Method

@Composable
fun MethodItem(
    method: Method,
    modifier: Modifier = Modifier,
    selected: Boolean,
    onItemClick: (Method) -> Unit
) {
    val color = if (selected) Color.Blue else Color.Transparent
    Row(
        modifier = modifier
            .background(color = color)
            .fillMaxWidth()
            .clickable {
                onItemClick(method)
            }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "Method Icon")
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = method.name,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Preview
@Composable
fun MethodItemPreview() {
    MethodItem(
        method = Method(
            id = 1,
            name = "V60",
            description = "V60 description"
        ),
        selected = false,
        onItemClick = {}
    )
}