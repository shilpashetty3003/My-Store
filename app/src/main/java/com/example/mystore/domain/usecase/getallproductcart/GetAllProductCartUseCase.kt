package com.example.mystore.domain.usecase.getallproductcart

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

class GetAllProductCartUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<List<FoodItem>> = repository.getAllProductCart()

}