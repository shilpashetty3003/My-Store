package com.example.mystore.data.repository

import android.util.Log
import androidx.room.TypeConverter
import com.example.mystore.data.local.ProductDatabase
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.domain.repository.LocalDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class LocalDataSourceImpl(
    productDatabase: ProductDatabase,
) : LocalDataSource {
    private val productDao = productDatabase.productDao()

    override suspend fun insertProducts(products: List<CategoryItem>) {
        productDao.insertProducts(products)
    }

    override fun getAllProduct(): Flow<List<CategoryItem>> {
        return productDao.getAllProducts()
    }


    override fun getAllProductCart(): Flow<List<FoodItem>> {

        val foodList = ArrayList<FoodItem>()
        val productItems = productDao.getAllProduct()
        for (i in productItems.indices) {
            for (j in 0 until productItems[i].items.size) {
                if (productItems[i].items[j].isCart) {
                    foodList.add(productItems[i].items[j])
                }
            }
        }

        Log.d("TAG", "getAllProductCart: $foodList ")
        return flowOf(foodList)
    }

    override fun getAllFavouriteCart(): Flow<List<FoodItem>> {
        val foodList = ArrayList<FoodItem>()
        val productItems = productDao.getAllProduct()
        for (i in productItems.indices) {
            for (j in 0 until productItems[i].items.size) {
                if (productItems[i].items[j].isFavourite) {
                    foodList.add(productItems[i].items[j])
                }
            }
        }

        Log.d("TAG", "getAllFavouriteCart: $foodList ")
        return flowOf(foodList)
    }

    override suspend fun deleteFavouriteCart(foodItem: FoodItem) {
        val productItems = productDao.getAllProduct()
        for (i in productItems.indices) {
            for (j in 0 until productItems[i].items.size) {
                if (productItems[i].items[j].id == foodItem.id) {
                    productItems[i].items[j].isFavourite = false
                    productDao.updateCard(productItems[i].id, Gson().toJson(productItems[i].items))
                    Log.d("TAG", "deleteFavouriteCart: "+Gson().toJson(productItems[i].items))
                    break;
                }
            }
        }
    }

    override suspend fun deleteCart(foodItem: FoodItem) {
        val productItems = productDao.getAllProduct()
        for (i in productItems.indices) {
            for (j in 0 until productItems[i].items.size) {
                if (productItems[i].items[j].id == foodItem.id) {
                    productItems[i].items[j].isCart = false
                    productDao.updateCard(productItems[i].id, Gson().toJson(productItems[i].items))
                    Log.d("TAG", "deleteFavouriteCart: "+Gson().toJson(productItems[i].items))
                    break;
                }
            }
        }
    }

    override suspend fun addCart(categoryId: Int, foodItem: FoodItem) {

        val category = productDao.getCategoryByID(categoryId)
        for (i in 0..category.items.size) {
            if (category.items[i].id == foodItem.id) {
                category.items.set(i, foodItem)
                break;
            }
        }
        Log.d("TAG", "addCart: ${category.items}")
        productDao.updateCard(categoryId, Gson().toJson(category.items))
    }

    override fun getSelectedProduct(productId: Int): Flow<FoodItem> {
        var foodList =FoodItem()
        val productItems = productDao.getAllProduct()
        for (i in productItems.indices) {
            for (j in 0 until productItems[i].items.size) {
                if (productItems[i].items[j].id ==productId) {
                    foodList =productItems[i].items[j]
                }
            }
        }
        return  flowOf(foodList)
    }


}