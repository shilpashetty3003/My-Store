package com.example.mystore.presentation.common.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.mystore.R
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.presentation.common.card.ProductCard
import com.example.mystore.ui.theme.DIMENS_16dp
import com.example.mystore.ui.theme.DIMENS_2dp
import com.example.mystore.ui.theme.DIMENS_8dp
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.GilroyFontFamily
import com.example.mystore.ui.theme.TEXT_SIZE_12sp
import com.example.mystore.ui.theme.TEXT_SIZE_24sp


@Composable
fun ListContentProduct(
    modifier: Modifier = Modifier,
    title: String,
    products: List<FoodItem>,
    navController: NavController,
    onFavouriteClick: (Boolean,FoodItem) -> Unit,
    onClickToCart: (FoodItem) -> Unit,

    ) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = DIMENS_16dp, end = DIMENS_16dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_SIZE_24sp,
                color = Black
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(id = R.string.see_all),
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_SIZE_12sp,
                color = DarkOrange
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DIMENS_2dp),
            contentPadding = PaddingValues(DIMENS_8dp)
        ) {
            items(products) { product ->
                ProductCard(
                    productItem = product,
                    navController = navController,
                    onFavouriteClick = onFavouriteClick,
                    onClickToCart = onClickToCart
                )
            }
        }
    }
}

