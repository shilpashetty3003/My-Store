package com.example.mystore.domain.usecase

import com.example.mystore.domain.usecase.deletecart.DeleteCartUseCase
import com.example.mystore.domain.usecase.deletefavouritecart.DeleteFavouriteCartUseCase
import com.example.mystore.domain.usecase.getallproduct.GetAllProductUseCase
import com.example.mystore.domain.usecase.getallproduct.ReadOnBoardingUseCase
import com.example.mystore.domain.usecase.getallproduct.SaveOnBoardingUseCase
import com.example.mystore.domain.usecase.getallproductcart.GetAllProductCartUseCase
import com.example.mystore.domain.usecase.getselectedproduct.GetSelectedProductUseCase
import com.example.mystore.domain.usecase.readfavouritecart.GetFavouriteCartUseCase
import com.example.mystore.domain.usecase.savecartproduct.SaveCartProductUseCase
import com.example.mystore.domain.usecase.saveproduct.InsertProductUseCase

data class UseCase(
    val getAllProductUseCase: GetAllProductUseCase,
    val insertProductsUseCase: InsertProductUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val insertCartUseCase: SaveCartProductUseCase,
    val readCartUseCase: GetAllProductCartUseCase,
    val readFavouriteCart: GetFavouriteCartUseCase,
    val deleteFavouriteCart: DeleteFavouriteCartUseCase,
    val deleteCart: DeleteCartUseCase,
    val readSelectedProductUseCase: GetSelectedProductUseCase,
)
