package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rochapires.coffeebrewer.R
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Recipe
import com.rochapires.coffeebrewer.util.Utils.formatDuration
import com.rochapires.coffeebrewer.util.Utils.formatWeight

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeItem(
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit = {},
    modifier: Modifier = Modifier,
    cardBackgroundColor:Color  = MaterialTheme.colors.surface
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if(expandedState) 180f else 0f)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = cardBackgroundColor,
        elevation = 5.dp,
        onClick = {
            onItemClick(recipe)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = recipe.name,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = { expandedState = !expandedState }
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                }
            }
            Divider(modifier = Modifier.padding(vertical = 10.dp))
            RecipeAmounts(recipe)
            if(expandedState) {
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = recipe.description,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun RecipeAmounts(
    recipe: Recipe,
    onCoffeeAmountClick: () -> Unit = {},
    onWaterAmountClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomRecipeItemInfo(
            modifier = Modifier.weight(3f),
            icon = Icons.Default.Star,
            iconContentDescription = "Coffee Amount",
            text = recipe.coffee_amount.formatWeight()
        )
        BottomRecipeItemInfo(
            modifier = Modifier.weight(3f),
            icon = Icons.Default.AccountCircle,
            iconContentDescription = "Water Amount",
            text = recipe.water_amount.formatWeight()
        )
        BottomRecipeItemInfo(
            modifier = Modifier.weight(3f),
            icon = Icons.Default.List,
            iconContentDescription = "Time",
            text = recipe.time.formatDuration()
        )
    }
}

@Composable
fun BottomRecipeItemInfo(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconContentDescription: String,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(imageVector = icon, contentDescription = iconContentDescription)
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = text,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            fontWeight = FontWeight.Light,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun RecipeItemPreview(){
    RecipeItem(recipe = Recipe(
        1,
        "Hoffmann's V60",
        "Hoffmann's V60 description Hoffmann's V60 description Hoffmann's V60 description Hoffmann's V60 description Hoffmann's V60 description",
        2.0,
        2.0,
        2.0,
        180,
        "",
        231,
        1
    ), onItemClick = {})
}