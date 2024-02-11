package com.example.cocktails.navigation

sealed class Screen(val route:String) {
    object Home: Screen("/home")
    object Detail: Screen("/detail")
}