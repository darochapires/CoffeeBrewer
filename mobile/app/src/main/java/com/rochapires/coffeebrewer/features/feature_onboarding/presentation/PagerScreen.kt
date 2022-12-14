package com.rochapires.coffeebrewer.features.feature_onboarding.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.rochapires.coffeebrewer.features.feature_onboarding.data.OnboardingData
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.components.PagerIndicator
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.MethodsScreen
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.recipes.RecipesScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScreen(navController: NavController) {
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = OnboardingData.onboardingItems[pagerState.currentPage].text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                PagerIndicator(
                    pages = pagerState.pageCount,
                    currentPage = pagerState.currentPage
                )
            }
            HorizontalPager(count = 4, state = pagerState) { page ->
                when (page) {
                    0, 2 -> {
                        HelloLandingScreen()
                    }
                    1 -> {
                        MethodsScreen()
                    }
                    3 -> {
                        RecipesScreen()
                    }
                }
            }
        }
        if(pagerState.currentPage != 3) {
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { }
            ) {
                Text(text = "Skip")
            }
        }
        else {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                onClick = { }
            ) {
                Text(text = "Get Started")
            }
        }
    }
}