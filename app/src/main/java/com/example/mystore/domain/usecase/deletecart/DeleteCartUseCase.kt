package com.example.mystore.domain.usecase.deletecart

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem

class DeleteCartUseCase(private val repository: Repository) {
    suspend operator fun invoke(foodItem: FoodItem) = repository.deleteCart(foodItem)
}