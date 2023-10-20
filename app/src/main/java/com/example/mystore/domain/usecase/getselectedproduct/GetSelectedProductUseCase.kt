package com.example.mystore.domain.usecase.getselectedproduct

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

class GetSelectedProductUseCase(private val repository: Repository) {

    operator fun invoke(productId: Int): Flow<FoodItem> =
        repository.getSelectedProduct(productId = productId)
}