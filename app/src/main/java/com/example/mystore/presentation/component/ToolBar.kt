package com.example.mystore.presentation.component

import android.util.Log
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mystore.R
import com.example.mystore.presentation.screens.cart.CartViewModel
import com.example.mystore.ui.theme.DIMENS_12dp
import com.example.mystore.ui.theme.DIMENS_16dp
import com.example.mystore.ui.theme.DIMENS_24dp
import com.example.mystore.ui.theme.GilroyFontFamily
import com.example.mystore.ui.theme.TEXT_SIZE_24sp

@Composable
fun Toolbar(
    cartSize: Int,
) {
    val gradient = Brush.verticalGradient(
        0.0f to colorResource(id = R.color.dark_orange),
        1.0f to colorResource(id = R.color.yellow),
        startY = 0.0f,
        endY = 100.0f
    )
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(
                gradient,
                shape = RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20)
            )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DIMENS_16dp, vertical = DIMENS_16dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Black,
                fontFamily = GilroyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_SIZE_24sp,
                textAlign = TextAlign.Center
            )
            Row {
                ShoppingCart(cartSize)
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCart(cartSize: Int) {
    BadgedBox(
        badge = {
            Badge(
                modifier = Modifier.offset(y = 7.dp),
                containerColor = Red
            ) {
                val badgeNumber = cartSize
                Text(
                    badgeNumber.toString(),
                    modifier = Modifier.semantics {
                        contentDescription = "$badgeNumber new notifications"
                    }
                )
            }
        }, modifier = Modifier.padding(end = DIMENS_16dp)
    ) {
        Icon(
            Icons.Outlined.ShoppingCart,
            contentDescription = "Favorite", modifier = Modifier.size(DIMENS_24dp)
        )
    }

}

@Preview
@Composable
fun DefaultToolBar() {
    Toolbar(4)
}