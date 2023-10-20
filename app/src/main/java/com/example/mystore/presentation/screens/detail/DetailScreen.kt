package com.example.mystore.presentation.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mystore.presentation.common.content.AddtoCartPrice
import com.example.mystore.presentation.common.content.FlowerAbout
import com.example.mystore.presentation.common.content.FlowerAddToCartButton
import com.example.mystore.presentation.common.content.FlowerTitleSubtitle
import com.example.mystore.presentation.common.content.HeaderImagesSlider
import com.example.mystore.presentation.common.content.TopAppBarWithBack
import com.example.mystore.ui.theme.DarkOrange
import com.example.mystore.ui.theme.GrayTextColor
import com.example.mystore.ui.theme.white

@Composable
fun DetailScreen(navController: NavHostController,detailViewModel: DetailViewModel = hiltViewModel()) {
    val mContext = LocalContext.current
    val selectedProduct by detailViewModel.selectedProduct.collectAsState()
    val pageCount by remember { mutableStateOf(0) }
    Scaffold(topBar = {
        TopAppBarWithBack(
            onBackClick = {
                navController.popBackStack()
            },
        )

    }, backgroundColor = GrayTextColor,
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {

                ConstraintLayout {
                    val (imagesliderref, addtocartref) = createRefs()
                    Box(modifier = Modifier
                        .height(250.dp)
                        .constrainAs(imagesliderref) {
                            top.linkTo(imagesliderref.top)
                            bottom.linkTo(imagesliderref.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                        selectedProduct?.let { HeaderImagesSlider(it) }
                    }
                    Surface(color = DarkOrange,
                        shape = RoundedCornerShape(40.dp)
                            .copy(
                                bottomStart = ZeroCornerSize,
                                bottomEnd = ZeroCornerSize
                            ), modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 300.dp)
                            .constrainAs(addtocartref) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                        ) {

                            selectedProduct?.name?.let { FlowerTitleSubtitle(it) }
//                            Spacer(modifier = Modifier.padding(10.dp))
                            selectedProduct?.price?.let { AddtoCartPrice(it) }
                            Spacer(modifier = Modifier.padding(10.dp))
                            Divider(color = white, thickness = 1.dp)
                            Spacer(modifier = Modifier.padding(20.dp))
                            FlowerAbout()
                            Spacer(modifier = Modifier.padding(20.dp))
                            FlowerAddToCartButton(navController)
                        }

                    }

                }
            }
        }
    )
}
