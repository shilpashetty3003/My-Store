package com.example.mystore.domain.usecase.readfavouritecart

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

class GetFavouriteCartUseCase(val repository: Repository) {

    operator fun invoke(): Flow<List<FoodItem>> = repository.getAllFavouriteCart()
}