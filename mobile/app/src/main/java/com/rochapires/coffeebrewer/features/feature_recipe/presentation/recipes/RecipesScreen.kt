package com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.features.common.components.ListPadding
import com.rochapires.coffeebrewer.features.common.components.TopBar
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.components.RecipeItem

@Composable
fun RecipesScreen(
    navController: NavController,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(
            lazyListState = lazyListState,
            title = state.methodName,
            navController = navController
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = ListPadding(lazyListState)),
            state = lazyListState
        ) {
            items(state.recipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onItemClick = {
                        viewModel.onEvent(RecipesEvent.ItemSelected(recipe))
                        navController.navigate(Screen.RecipeDetailScreen.route + "/${recipe.id}")
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text (
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
