package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rochapires.coffeebrewer.features.common.components.TopBar
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail.components.StepItem
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.components.RecipeItem
import com.rochapires.coffeebrewer.util.Utils.formatDuration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(
    navController: NavController? = null,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    val composableScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                lazyListState = lazyListState,
                title = if (state.recipe != null) state.recipe.name else "",
                navController = navController,
                actions = { TopBarActions(viewModel) }
            )
        },
        floatingActionButton = {
            state.recipe?.let {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(RecipeDetailEvent.StartRecipePressed)
                        composableScope.launch {
                            viewModel.state.value.currentStepIndex?.let { index ->
                                lazyListState.scrollToItem(
                                    index = index,
                                    scrollOffset = 0
                                )
                            }
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Start recipe")
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (state.brewTimerIsVisible) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    BrewTimer(
                        totalTime = viewModel.state.value.brewTimerTotalTime * 1000L,
                        brewTimerIsRunning = viewModel.state.value.brewTimerIsRunning,
                        stepFinished = {
                            viewModel.onEvent(RecipeDetailEvent.StepFinished)
                            if(viewModel.state.value.brewTimerIsRunning) {
                                composableScope.launch {
                                    viewModel.state.value.currentStepIndex?.let { index ->
                                        lazyListState.scrollToItem(
                                            index = index,
                                            scrollOffset = 0
                                        )
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Brew finished. Enjoy you cup! \nIf necessary, adjust grind size",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 105.dp),
                state = lazyListState
            ) {
                if (!state.brewTimerIsVisible) {
                    item {
                        if (state.recipe != null) {
                            RecipeItem(
                                recipe = state.recipe,
                                cardBackgroundColor = Color.Transparent
                            )
                        }
                    }
                }
                itemsIndexed(state.steps) { index, step ->
                    StepItem(
                        step = step,
                        selected = viewModel.state.value.currentStepIndex == index
                    )
                }
            }

            //TODO move these to components and reuse
            if (state.error.isNotBlank()) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}

@Composable
fun BrewTimer(
    totalTime: Long,
    brewTimerIsRunning: Boolean = false,
    stepFinished: () -> Unit,
    remainingTimeColor: Color = MaterialTheme.colors.primary,
    passedTimeColor: Color = Color.DarkGray,
    modifier: Modifier = Modifier.size(200.dp),
    strokeWidth: Dp = 5.dp
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var value by remember { mutableStateOf(1f) }
    var currentTime by remember { mutableStateOf(totalTime) }
    var currentTimeCircle by remember { mutableStateOf(totalTime) }
    var isTimerRunning by remember { mutableStateOf(brewTimerIsRunning) }
    var delayAggregate by remember { mutableStateOf(0L) }
    var isNewStep by remember { mutableStateOf(false) }

    if(isNewStep) {
        isNewStep = false
        isTimerRunning = brewTimerIsRunning
        if(isTimerRunning) {
            currentTime = totalTime
            currentTimeCircle = totalTime
        }
    }

    LaunchedEffect(key1 = currentTimeCircle, key2 = isTimerRunning) {
        if(!isTimerRunning) {
            return@LaunchedEffect
        }

        if (currentTimeCircle > 0L) {
            delay(100L)
            currentTimeCircle -= 100L
            value = currentTimeCircle / totalTime.toFloat()
            delayAggregate += 100L
            if(delayAggregate == 1000L) {
                delayAggregate = 0
                currentTime -= 1000L
            }
        } else {
            stepFinished()
            isNewStep = true
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(20.dp)
            .onSizeChanged {
                size = it
            },
    ) {
        Canvas(modifier = modifier) {
            drawCircle(
                color = passedTimeColor,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = remainingTimeColor,
                startAngle = 90f,
                sweepAngle = 360f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentTime / 1000L).formatDuration(
                shouldShowUnit = false
            ),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun TopBarActions(viewModel: RecipeDetailViewModel) {
    SetDefaultRecipe(
        onClick = {
            viewModel.onEvent(RecipeDetailEvent.SetAsDefaultPressed)
        }
    )
    LikeRecipe(
        onClick = {
            viewModel.onEvent(RecipeDetailEvent.LikePressed)
        }
    )
}

@Composable
fun SetDefaultRecipe(
    onClick: () -> Unit
) {
    IconButton(
        onClick = {
            onClick()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Set Default"
        )
    }
}

@Composable
fun LikeRecipe(
    onClick: () -> Unit
) {
    IconButton(
        onClick = {
            onClick()
        }
    ) {
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = "Like"
        )
    }
}

/*
@Preview
@Composable
fun BrewTimerPreview() {
    BrewTimer(totalTime = 8L * 1000L, {})
}*/