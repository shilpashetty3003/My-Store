package com.example.mystore.presentation.screens.favourite

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.presentation.common.content.ListContentCart
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun FavouriteScreen(
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
) {
    favouriteViewModel.getFavouriteProducts()
    val favouriteProduct by favouriteViewModel.favouriteList.collectAsState()
    Log.d("TAG", "FavouriteScreen: $favouriteProduct")

    ListContentCart(
        cartProducts = favouriteProduct, onClickToCart = {

        },
        onClickDeleteCart = { productItem ->
            favouriteViewModel.deleteFavouriteCart(productItem)
        }
    )
}
