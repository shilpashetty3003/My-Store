package com.example.mystore.domain.usecase.savecartproduct

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem


class SaveCartProductUseCase(private val repository: Repository) {
    suspend operator fun invoke(categoryId:Int, foodItem: FoodItem) = repository.insertCart(categoryId,foodItem)

}