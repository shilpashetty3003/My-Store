package com.example.mystore.presentation.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.ui.theme.DIMENS_10dp
import com.example.mystore.ui.theme.DIMENS_14dp
import com.example.mystore.ui.theme.DIMENS_16dp
import com.example.mystore.ui.theme.DIMENS_1dp
import com.example.mystore.ui.theme.DIMENS_248dp
import com.example.mystore.ui.theme.DIMENS_32dp
import com.example.mystore.ui.theme.DIMENS_46dp
import com.example.mystore.ui.theme.DIMENS_4dp
import com.example.mystore.ui.theme.DIMENS_64dp
import com.example.mystore.ui.theme.DIMENS_8dp
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.GilroyFontFamily
import com.example.mystore.ui.theme.GrayBorderStroke
import com.example.mystore.ui.theme.GraySecondTextColor
import com.example.mystore.ui.theme.GrayTextColor
import com.example.mystore.ui.theme.TEXT_SIZE_12sp
import com.example.mystore.ui.theme.TEXT_SIZE_16sp
import com.example.mystore.ui.theme.TEXT_SIZE_18sp

@Composable
fun ContentCart(
    modifier: Modifier = Modifier,
    productItem: FoodItem,
    onClickDeleteCart: (FoodItem) -> Unit,
    onClickToCart: (foodItem: FoodItem) -> Unit,
) {

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(
            DIMENS_10dp
        )
    ) {
        Row(modifier = Modifier.padding(DIMENS_4dp)) {
            Image(
                modifier = Modifier
                    .size(width = DIMENS_64dp, height = DIMENS_64dp)
                    .padding(start = DIMENS_8dp),
                painter = rememberAsyncImagePainter(productItem.icon),
                contentDescription = stringResource(id = R.string.app_name)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = DIMENS_16dp),
            ) {
                Text(
                    text = productItem.name,
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    fontSize = TEXT_SIZE_16sp
                )

                Spacer(modifier = Modifier.height(DIMENS_4dp))

                Text(
                    text = productItem.price.toString(),
                    fontFamily = GilroyFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = GraySecondTextColor,
                    fontSize = TEXT_SIZE_12sp,
                )
            }

            Button(
                modifier = Modifier
                    .size(DIMENS_32dp)
                    .align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(backgroundColor = DarkOrange),
                shape = RoundedCornerShape(DIMENS_8dp),
                contentPadding = PaddingValues(DIMENS_4dp),
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
//            Text(
//                modifier = Modifier.align(Alignment.CenterVertically),
//                text = "$${productItem.price}",
//                fontFamily = GilroyFontFamily,
//                fontWeight = FontWeight.Bold,
//                color = Black,
//                fontSize = TEXT_SIZE_18sp,
//            )

            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = DIMENS_16dp, end = DIMENS_16dp)
                    .clickable {
                        onClickDeleteCart.invoke(productItem)
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.app_name),
                colorFilter = ColorFilter.tint(color = Color.DarkGray)
            )

        }
    }

//    Column {
//        Divider(modifier = Modifier.height(DIMENS_1dp), color = GrayBorderStroke)
//
//        Row(
//            modifier = modifier
//                .background(color = GrayBorderStroke)
//                .clip(shape = RoundedCornerShape(20.dp))
//
//        ) {
//            Image(
//                modifier = Modifier
//                    .size(width = DIMENS_64dp, height = DIMENS_64dp)
//                    .padding(start = DIMENS_8dp),
//                painter = rememberAsyncImagePainter(productItem.icon),
//                contentDescription = stringResource(id = R.string.app_name)
//            )
//
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .align(Alignment.CenterVertically)
//                    .padding(start = DIMENS_16dp),
//            ) {
//                Text(
//                    text = productItem.name,
//                    fontFamily = GilroyFontFamily,
//                    fontWeight = FontWeight.Bold,
//                    color = Black,
//                    fontSize = TEXT_SIZE_16sp
//                )
//
//                Spacer(modifier = Modifier.height(DIMENS_4dp))
//
//                Text(
//                    text = productItem.price.toString(),
//                    fontFamily = GilroyFontFamily,
//                    fontWeight = FontWeight.Medium,
//                    color = GraySecondTextColor,
//                    fontSize = TEXT_SIZE_12sp,
//                )
//            }
//
//            Text(
//                modifier = Modifier.align(Alignment.CenterVertically),
//                text = "$${productItem.price}",
//                fontFamily = GilroyFontFamily,
//                fontWeight = FontWeight.Bold,
//                color = Black,
//                fontSize = TEXT_SIZE_18sp,
//            )
//
//            Image(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(start = DIMENS_16dp, end = DIMENS_16dp)
//                    .clickable {
//                        onClickDeleteCart.invoke(productItem)
//                    },
//                imageVector = Icons.Default.Delete,
//                contentDescription = stringResource(R.string.app_name),
//                colorFilter = ColorFilter.tint(color = Color.DarkGray)
//            )
//
//        }
//    }

}


@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(DIMENS_248dp),
            painter = painterResource(id = R.drawable.img_empty_content),
            contentDescription = stringResource(R.string.image_content_empty)
        )

        Spacer(modifier = Modifier.height(DIMENS_16dp))

        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(R.string.oops_no_data),
            fontFamily = GilroyFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = TEXT_SIZE_18sp,
            color = Black,
            textAlign = TextAlign.Center,
        )
    }
}