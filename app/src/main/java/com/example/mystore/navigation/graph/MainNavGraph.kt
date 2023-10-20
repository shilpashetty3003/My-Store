package com.example.mystore.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.mystore.navigation.screen.BottomNavItemScreen
import com.example.mystore.navigation.screen.Screen
import com.example.mystore.presentation.screens.about.AboutScreen
import com.example.mystore.presentation.screens.cart.CartScreen
import com.example.mystore.presentation.screens.detail.DetailScreen
import com.example.mystore.presentation.screens.favourite.FavouriteScreen
import com.example.mystore.presentation.screens.home.HomeScreen
import com.example.mystore.utils.Constants.PRODUCT_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomNavItemScreen.Home.route
    ) {
        composable(route = BottomNavItemScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomNavItemScreen.Explore.route) {

            FavouriteScreen()
        }
        composable(route = BottomNavItemScreen.Cart.route) {
            CartScreen()
        }

        detailsNavGraph(navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = Screen.Details.route
    ) {
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(PRODUCT_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(navController)
        }
    }
}