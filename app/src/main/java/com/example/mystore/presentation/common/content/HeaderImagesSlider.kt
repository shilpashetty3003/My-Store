package com.example.mystore.presentation.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mystore.R
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.navigation.screen.Screen
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.white

@Composable
fun HeaderImagesSlider(foodItem: FoodItem) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {

        Box(modifier = Modifier.fillMaxHeight()) {

            Image(
                painter = rememberAsyncImagePainter(foodItem.icon),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 20.dp)
            )
        }
    }
}


@Composable
fun FlowerAddToCartButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
               // navController.navigate(Screen.Details.passProductId(4))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = white),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Add to Cart",
                color = DarkOrange,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = DarkOrange,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(20.dp, 20.dp)
            )
        }
    }
}


@Composable
fun FlowerTitleSubtitle(name:String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = name,
            style = MaterialTheme.typography.h6,
            color = white
        )

//        Text(
//                text = "includes jannein flower, lily leaves",
//        style = MaterialTheme.typography.caption,
//        color = white
//        )
    }
}


@Composable
fun AddtoCartPrice(price:Double) {
    val counter = remember {
        mutableStateOf(1)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${price}",
            color = white,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .width(110.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(white)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (counter.value != 1) {
                        counter.value--
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_minimize_24),
                        contentDescription = "",
                        modifier = Modifier.padding(bottom = 15.dp),
                        tint = DarkOrange
                    )
                }

                Text(
                    text = "${counter.value}",
                    color = DarkOrange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                IconButton(onClick = {
                    counter.value++
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = DarkOrange,
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun FlowerAbout() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "About",
            style = MaterialTheme.typography.h6,
            color = white
        )
        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "Lorem ipsum is simply dummy text of the printing and \n" +
                    "typesetting industry. Loremk ipsum has been the industy's \n" +
                    "standard dummy text ever since the 1500s.",
            style = MaterialTheme.typography.caption,
            color = white
        )
    }
}

@Preview
@Composable
fun FlowerAddToCartButtonPreview() = FlowerAddToCartButton(NavController(LocalContext.current))