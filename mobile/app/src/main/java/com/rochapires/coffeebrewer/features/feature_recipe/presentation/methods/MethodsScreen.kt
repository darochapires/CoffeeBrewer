package com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.components.MethodItem

@Composable
fun MethodsScreen(
    //navController: NavController,
    viewModel: MethodsViewModel = hiltViewModel(),
    isLanding: Boolean = false
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.methods) { method ->
                MethodItem(
                    method = method,
                    selected = isLanding && viewModel.selectedItem != null  && viewModel.selectedItem == method.id,
                    onItemClick = {
                        viewModel.onEvent(MethodsEvent.ItemSelected(method, isLanding))
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