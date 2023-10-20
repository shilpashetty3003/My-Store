package com.example.mystore.domain.usecase.saveproduct

import com.example.mystore.data.repository.Repository
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem

class InsertProductUseCase(private val repository: Repository) {

    suspend operator fun invoke(products: List<CategoryItem>) = repository.insertProducts(products)

}