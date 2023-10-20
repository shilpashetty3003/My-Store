package com.example.mystore.data.repository

import com.example.mystore.data.local.ProductDatabase
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.domain.repository.LocalDataSource
import com.example.mystore.domain.repository.OnBoardingOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: OnBoardingOperations,
    private val localDataSource: LocalDataSource,
) {
    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.saveOnBoardingState(isCompleted = isCompleted)
    }

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    suspend fun insertProducts(products: List<CategoryItem>) =
        localDataSource.insertProducts(products)

    fun getAllProduct(): Flow<List<CategoryItem>> = localDataSource.getAllProduct()

    fun getAllProductCart(): Flow<List<FoodItem>> = localDataSource.getAllProductCart()
    fun getAllFavouriteCart(): Flow<List<FoodItem>> = localDataSource.getAllFavouriteCart()
    suspend fun insertCart(categoryId: Int, foodItem: FoodItem) =
        localDataSource.addCart(categoryId, foodItem)

    suspend fun deleteFavouriteCart(foodItem: FoodItem) =
        localDataSource.deleteFavouriteCart(foodItem)

    suspend fun deleteCart(foodItem: FoodItem) = localDataSource.deleteCart(foodItem)
    fun getSelectedProduct(productId: Int):Flow<FoodItem> = localDataSource.getSelectedProduct(productId)


}