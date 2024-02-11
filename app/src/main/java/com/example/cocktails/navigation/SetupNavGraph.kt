package com.example.cocktails.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cocktails.data.model.DrinkItem
import com.example.cocktails.ui.screens.DrinkDetailScreen.DrinkDetailScreen
import com.example.cocktails.ui.screens.HomeScreen.HomeScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            HomeScreen(onclick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "drinkItem",
                    value = it
                )
                navController.navigate(Screen.Detail.route)
            })
        }

        composable(route = Screen.Detail.route){
            val drink = navController.previousBackStackEntry?.savedStateHandle?.get<DrinkItem>("drinkItem")
            DrinkDetailScreen(
                drink = drink?: DrinkItem(),
                backClicked = { navController.popBackStack() }
            )
        }

    }
}