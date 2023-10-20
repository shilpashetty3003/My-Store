package com.example.mystore.domain.usecase.getallproduct

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

class GetAllProductUseCase(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<List<CategoryItem>> = repository.getAllProduct()
}