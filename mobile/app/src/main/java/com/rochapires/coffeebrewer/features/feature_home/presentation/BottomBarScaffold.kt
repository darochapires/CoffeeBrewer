package com.rochapires.coffeebrewer.features.feature_home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rochapires.coffeebrewer.features.common.Screen
import com.rochapires.coffeebrewer.navigation.HomeNavigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarScaffold(navController:NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = Screen.HomeScreen.route,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Methods",
                        route = Screen.MethodsScreen.route,
                        icon = Icons.Default.List
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = Screen.SettingsScreen.route,
                        icon = Icons.Default.Settings
                    )
                ),
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    /*val shouldShowBottomBar = items.any { item ->
        item.route == backStackEntry.value?.destination?.route
    }
    if(!shouldShowBottomBar) return*/


    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = backStackEntry?.destination?.hierarchy?.any { it.route == item.route} == true
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    //if(!selected) {
                        onItemClick(item)
                    //}
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}