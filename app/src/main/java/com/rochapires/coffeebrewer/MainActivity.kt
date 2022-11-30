package com.rochapires.coffeebrewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rochapires.coffeebrewer.ui.theme.CoffeeBrewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeBrewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                    /*val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MethodsScreen.route
                    ) {
                        composable(route = Screen.MethodsScreen.route) {
                            MethodsScreen(navController)
                        }
                    }*/
                }
            }
        }

        
        //dataStore = createDataStore//createDataStore()
    }
}