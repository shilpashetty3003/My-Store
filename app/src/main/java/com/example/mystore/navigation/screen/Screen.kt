package com.example.mystore.navigation.screen

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Details : Screen("details_screen/{productId}") {
        fun passProductId(productId: Int): String = "details_screen/$productId"
    }
    object OnBoarding : Screen("on_boarding_screen")
}