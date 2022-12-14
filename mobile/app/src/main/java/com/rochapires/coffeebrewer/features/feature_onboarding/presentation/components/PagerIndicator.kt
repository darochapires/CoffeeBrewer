package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(pages: Int, currentPage: Int) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ){
        repeat(pages) { page ->
            Indicator(
                isSelected = page == currentPage
            )
        }
    }

}

@Composable
fun Indicator(
    isSelected: Boolean
){
    val width = animateDpAsState(targetValue = if(isSelected) 40.dp else 10.dp)
    Box(
        modifier = Modifier.padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if(isSelected) Color.Blue else Color.Gray.copy(alpha = 0.5f)
            )
    )
}