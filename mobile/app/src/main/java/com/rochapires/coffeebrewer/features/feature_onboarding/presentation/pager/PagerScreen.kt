package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.pager

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.features.feature_onboarding.data.OnboardingData
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.HelloLandingScreen
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.coffee_quantity.CoffeeQuantityScreen
import com.rochapires.coffeebrewer.features.feature_onboarding.presentation.components.PagerIndicator
import com.rochapires.coffeebrewer.features.feature_recipe.presentation.methods.MethodsScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScreen(
    navController: NavController,
    viewModel: PagerViewModel = hiltViewModel()
) {
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
            HorizontalPager(
                count = OnboardingData.onboardingItems.count(),
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> {
                        HelloLandingScreen()
                    }
                    1 -> {
                        MethodsScreen(navController, isLanding = true)
                    }
                    2 -> {
                        CoffeeQuantityScreen(navController)
                    }
                }
            }
        }
        if(pagerState.currentPage != 2) {
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                    viewModel.onEvent(PagerEvent.SkipButtonClicked())
                }
            ) {
                Text(text = "Skip")
            }
        }
    }
}