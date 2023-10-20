package com.example.mystore.presentation.common.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.navigation.screen.Screen
import com.example.mystore.ui.theme.DIMENS_10dp
import com.example.mystore.ui.theme.DIMENS_14dp
import com.example.mystore.ui.theme.DIMENS_16dp
import com.example.mystore.ui.theme.DIMENS_174dp
import com.example.mystore.ui.theme.DIMENS_20dp
import com.example.mystore.ui.theme.DIMENS_24dp
import com.example.mystore.ui.theme.DIMENS_46dp
import com.example.mystore.ui.theme.DIMENS_6dp
import com.example.mystore.ui.theme.DIMENS_80dp
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.GilroyFontFamily
import com.example.mystore.ui.theme.GrayBorderStroke
import com.example.mystore.ui.theme.GraySecondTextColor
import com.example.mystore.ui.theme.TEXT_SIZE_12sp
import com.example.mystore.ui.theme.TEXT_SIZE_16sp
import com.example.mystore.ui.theme.TEXT_SIZE_18sp
import com.example.mystore.ui.theme.DIMENS_12dp as DIMENS_12dp1

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    productItem: FoodItem,
    navController: NavController,
    onFavouriteClick: (Boolean, FoodItem) -> Unit,
    onClickToCart: (FoodItem) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(DIMENS_12dp1),
        border = BorderStroke(width = 1.dp, color = GrayBorderStroke),
        modifier = modifier
            .padding(DIMENS_12dp1)
            .width(DIMENS_174dp)
            .clickable {
                navController.navigate(Screen.Details.passProductId(productId = productItem.id))
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DIMENS_12dp1)
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(productItem.icon),
                    contentDescription = stringResource(R.string.image_product),
                    modifier = Modifier
                        .weight(0.8f)
                        .height(DIMENS_80dp)
                )
                FavoriteButton(isFavorite = productItem.isFavourite) {
                    onFavouriteClick(it, productItem)
                }
            }

            Spacer(modifier = Modifier.height(DIMENS_24dp))

            Text(
                text = productItem.name,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.Bold,
                color = Black,
                fontSize = TEXT_SIZE_16sp
            )

            Spacer(modifier = Modifier.height(DIMENS_6dp))


            Spacer(modifier = Modifier.height(DIMENS_20dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "$${productItem.price}",
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = TEXT_SIZE_18sp
                )

                Button(
                    modifier = Modifier.size(DIMENS_46dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = DarkOrange),
                    shape = RoundedCornerShape(DIMENS_14dp),
                    contentPadding = PaddingValues(DIMENS_10dp),
                    onClick = {
                        onClickToCart.invoke(productItem)
                    }
                )
                {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Add,
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.add)
                    )
                }
            }

        }
    }
}


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    isFavorite: Boolean,
    onFavouriteClick: (Boolean) -> Unit,
) {
    var isFavorite by remember {
        mutableStateOf(isFavorite)
    }
    IconToggleButton(
        modifier = modifier,
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = it
            onFavouriteClick(it)
        }
    ) {
        androidx.compose.material3.Icon(
            tint = color,
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}