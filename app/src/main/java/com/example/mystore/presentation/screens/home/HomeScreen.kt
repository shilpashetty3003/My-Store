package com.example.mystore.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mystore.presentation.common.content.ListContentProduct
import com.example.mystore.presentation.component.SliderBanner
import com.example.mystore.presentation.component.Toolbar
import com.example.mystore.presentation.screens.cart.CartViewModel
import com.example.mystore.utils.showToastShort

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    cartViewModel.getCartList()
    val cartProducts by cartViewModel.productList.collectAsState()
    val allProducts by homeViewModel.productList.collectAsState()
    val mContext = LocalContext.current
    Scaffold { padding ->

        LazyColumn(
            modifier = Modifier
                .heightIn(2000.dp)
                .padding(padding)

        ) {
            item {
                Toolbar(cartProducts.size)
                SliderBanner()
            }
            items(allProducts) { category ->
                ListContentProduct(
                    title = category.name,
                    products = category.items,
                    navController = navController, onFavouriteClick = { isFavourite, foodItem ->
                        Log.d("TAG", "HomeScreen:  $isFavourite")
                        foodItem.isFavourite = isFavourite
                        homeViewModel.addCart(category.id, foodItem)
                    }
                ) { foodItem ->
                    foodItem.isCart = true
                    homeViewModel.addCart(category.id, foodItem)
                    cartViewModel.getCartList()
                    mContext.showToastShort("Item successfully added to cart")
                }
            }
        }
    }
}