package com.rochapires.coffeebrewer.features.common.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rochapires.coffeebrewer.features.common.Constants.TOP_BAR_ANIMATION_DURATION
import com.rochapires.coffeebrewer.features.common.Constants.TOP_BAR_HEIGHT

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState? = null,
    title: String = "",
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .animateContentSize(animationSpec = tween(durationMillis = TOP_BAR_ANIMATION_DURATION))
            .height(height = if (lazyListState != null && !lazyListState.isScrollingUp()) 0.dp else TOP_BAR_HEIGHT),
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun ListPadding(lazyListState: LazyListState, expandedPadding: Dp = TOP_BAR_HEIGHT): Dp {
    val padding by animateDpAsState(
        targetValue = if (!lazyListState.isScrollingUp()) 0.dp else expandedPadding,
        animationSpec = tween(durationMillis = TOP_BAR_ANIMATION_DURATION)
    )
    return padding
}
