package com.example.mystore.domain.repository

import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertProducts(products: List<CategoryItem>)
    fun getAllProduct(): Flow<List<CategoryItem>>
    fun getAllProductCart(): Flow<List<FoodItem>>
    fun getAllFavouriteCart(): Flow<List<FoodItem>>
    suspend fun deleteFavouriteCart(foodItem: FoodItem)
    suspend fun deleteCart(foodItem: FoodItem)
    suspend fun addCart(categoryId: Int, foodItem: FoodItem)
    fun getSelectedProduct(productId: Int): Flow<FoodItem>

//    fun searchProduct(query: String): Flow<List<CategoryItem>>
}