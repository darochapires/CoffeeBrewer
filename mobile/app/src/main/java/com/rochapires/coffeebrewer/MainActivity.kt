package com.rochapires.coffeebrewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rochapires.coffeebrewer.di.AppModule
import com.rochapires.coffeebrewer.navigation.RootNavigation
import com.rochapires.coffeebrewer.ui.theme.CoffeeBrewerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeBrewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //TODO find a better way to fetch onboardingDone
                    var onboardingDone: Boolean
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            val onboardingUseCases = AppModule.provideOnboardingUseCase(AppModule.provideUserPreferencesRepository(this@MainActivity))
                            onboardingDone = onboardingUseCases.getOnboardingDoneUseCase()
                        }
                    }
                    RootNavigation(onboardingDone)
                }
            }
        }
    }
}