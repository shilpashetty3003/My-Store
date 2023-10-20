package com.example.mystore.presentation.common.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.ui.theme.DIMENS_32dp
import com.example.mystore.ui.theme.DIMENS_8dp

@Composable
fun ListContentCart(
    modifier: Modifier = Modifier,
    cartProducts: List<FoodItem>,
    onClickDeleteCart: (FoodItem) -> Unit,
    onClickToCart: (FoodItem) -> Unit,
) {
    if (cartProducts.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = DIMENS_32dp),
            verticalArrangement = Arrangement.spacedBy(DIMENS_8dp)
        ) {
            items(cartProducts) { items ->
                ContentCart(
                    productItem = items, onClickToCart =
                    {
                        onClickToCart(it)
                    },
                    onClickDeleteCart = { productItem ->
                        onClickDeleteCart.invoke(productItem)
                    }
                )
            }
        }
    } else {
        EmptyContent()
    }
}