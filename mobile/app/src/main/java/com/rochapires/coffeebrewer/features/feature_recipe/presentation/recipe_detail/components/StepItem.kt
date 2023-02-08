package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.Step
import com.rochapires.coffeebrewer.features.feature_recipe.domain.model.StepType
import com.rochapires.coffeebrewer.util.Utils.formatDuration

@Composable
fun StepItem(
    step: Step,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Test")
                Text(
                    modifier = Modifier.padding(start = 7.dp),
                    text = step.title,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                step.waterAmount?.let { waterAmount ->
                    Text(
                        modifier = Modifier,
                        text = "${waterAmount}g",
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    modifier = Modifier,
                    text = "${step.durationInSeconds.formatDuration()}",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            step.description?.let { description ->
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    modifier = Modifier,
                    text = description,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview
@Composable
fun StepItemPreview(){
    StepItem(
        step = Step(
            id = 1,
            order = 1,
            stepType = StepType.Pour,
            title = "Pour Water",
            description = "Pour 26 g of water slowly in a circular motion",
            durationInSeconds = 9,
            recipeId = 1,
            waterAmount = 40
        ), selected = false
    )
}