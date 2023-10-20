package com.example.mystore.presentation.screens.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.presentation.common.content.EmptyContent
import com.example.mystore.presentation.screens.home.HomeViewModel
import com.example.mystore.ui.theme.DIMENS_16dp
import com.example.mystore.ui.theme.DIMENS_4dp
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.Orange
import com.example.mystore.ui.theme.black
import com.example.mystore.ui.theme.ghost_white
import com.example.mystore.ui.theme.white

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel(),
) {


    cartViewModel.getCartList()
    val allProducts by cartViewModel.productList.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        ConstraintLayout {
            val (cartitemsbgref, checkoutref) = createRefs()

            Box(modifier = Modifier
                .height(100.dp)
                .constrainAs(cartitemsbgref) {
                    top.linkTo(cartitemsbgref.top)
                    bottom.linkTo(cartitemsbgref.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                HeaderCartItems()
            }

            Surface(color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ), modifier = Modifier
                    .padding(top = 70.dp)
                    .constrainAs(checkoutref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                if (allProducts.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {

                        items(allProducts) { foodItem ->
                            Log.d("TAG", "CartScreen: $foodItem ")
                            ItemsFlower(foodItem) {
                                cartViewModel.deleteCart(it)
                            }
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                        item {
                            CheckoutDetails()
                        }
                    }
                } else {
                    EmptyContent()
                }
            }
        }
    }

}

@Preview
@Composable
fun HeaderCartItems() {
    val gradient = Brush.verticalGradient(
        0.0f to colorResource(id = R.color.dark_orange),
        1.0f to colorResource(id = R.color.yellow),
        startY = 0.0f,
        endY = 100.0f
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                gradient,

                )
    )
//    Image(
//        painter = painterResource(id = R.drawable.),
//        contentDescription = "login bg",
//        contentScale = ContentScale.FillWidth,
//        modifier = Modifier.fillMaxSize()
//    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        IconButton(onClick = { }) {
//            Icon(
//                modifier = Modifier.size(32.dp, 32.dp),
//                imageVector = Icons.Default.KeyboardArrowLeft,
//                contentDescription = "",
//                tint = white
//            )
//        }

//        Text(
//            text = "Cart Items",
//            color = white,
//            modifier = Modifier.padding(),
//            fontWeight = FontWeight.Bold,
//            fontSize = 16.sp,
//            textAlign = TextAlign.Center
//        )
    }
}


@Composable
fun ItemsFlower(foodItem: FoodItem, onClickDeleteCart: (foodItem: FoodItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
            ) {
                Image(
                    modifier = Modifier
                        .size(70.dp),
                    painter = rememberAsyncImagePainter(foodItem.icon),
                    contentDescription = "",
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(0.9f)
                    .wrapContentHeight()
                    .padding(start = DIMENS_4dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {
                    Text(
                        text = foodItem.name,
                        fontSize = 16.sp,
                        color = black,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2, modifier = Modifier.weight(0.8f)
                    )
                    Image(
                        modifier = Modifier
                            .padding(start = DIMENS_16dp, end = DIMENS_16dp)
                            .clickable {
                                onClickDeleteCart.invoke(foodItem)
                            },
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.app_name),
                        colorFilter = ColorFilter.tint(color = Color.DarkGray)
                    )
                }

                Text(
                    text = foodItem.price.toString(),
                    fontSize = 16.sp,
                    color = DarkOrange,
                )

                val counter = remember {
                    mutableStateOf(1)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Quantity:",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE5F4EF))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(DarkOrange)
                                    .size(32.dp, 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = {
                                    if (counter.value != 1) counter.value--

                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_minimize_24),
                                        contentDescription = "",
                                        modifier = Modifier.padding(bottom = 15.dp),
                                        tint = white
                                    )
                                }
                            }

                            Text(
                                text = "${counter.value}",
                                color = DarkOrange,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(DarkOrange)
                                    .size(32.dp, 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = {
                                    counter.value++
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "",
                                        tint = white,
                                        modifier = Modifier.size(20.dp, 20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckoutDetails() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Text(
                text = "Price Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Jannien Flower Bouquet",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "1 x $567.00",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Jannien Flower Bouquet",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "1 x $567.00",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Delivery Charges",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "$50.00",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Coupon Discount",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "$184.00",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Divider(color = Color.Gray, thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Total Amount Payable",
                    fontSize = 14.sp,
                    color = black,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$1000.00",
                    fontSize = 14.sp,
                    color = DarkOrange
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = DarkOrange),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Checkout",
                        color = white,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = white,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(20.dp, 20.dp)
                    )
                }
            }
        }
    }
}