package com.example.mystore.domain.usecase.deletefavouritecart

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem


class DeleteFavouriteCartUseCase(private val repository: Repository) {
    suspend operator fun invoke(foodItem: FoodItem) {
        repository.deleteFavouriteCart(foodItem)
    }
}