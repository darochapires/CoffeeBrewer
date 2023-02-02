package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipe_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopBar(
                lazyListState = lazyListState,
                title = if(state.recipe != null) state.recipe.name else "",
                navController = navController
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(RecipeDetailEvent.StartRecipePressed)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Start recipe")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (viewModel.state.value.brewTimerIsVisible) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    BrewTimer(
                        totalTime = state.brewTimerTotalTime * 1000L,
                        stepFinished = {
                            viewModel.onEvent(RecipeDetailEvent.StepFinished)
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
                if (!viewModel.state.value.brewTimerIsVisible) {
                    item {
                        if (state.recipe != null) {
                            RecipeItem(
                                recipe = state.recipe,
                                cardBackgroundColor = Color.Transparent
                            )
                        }
                    }
                }
                items(state.steps) { step ->
                    StepItem(
                        step = step
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
    stepFinished: () -> Unit,
    remainingTimeColor: Color = MaterialTheme.colors.primary,
    passedTimeColor: Color = Color.Gray,
    modifier: Modifier = Modifier.size(200.dp),
    strokeWidth: Dp = 5.dp
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var value by remember { mutableStateOf(1f) }
    var currentTime by remember { mutableStateOf(totalTime) }
    var isTimerRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = currentTime) {
        delay(100L)
        currentTime -= 100L
        value = currentTime/totalTime.toFloat()
        if(currentTime == 0L) {
            stepFinished()
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

/*@Preview
@Composable
fun BrewTimerPreview() {
    BrewTimer(totalTime = 40L * 1000L)
}*/