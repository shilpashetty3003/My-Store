package com.example.mystore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(products: List<CategoryItem>)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<CategoryItem>>

    @Query("SELECT items FROM product_table where id=:id")
    fun getItemCategoryById(id: Int): String

    @Query("SELECT * FROM product_table")
    fun getAllProduct(): List<CategoryItem>

    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getCategoryByID(id: Int): CategoryItem


    @Query("UPDATE product_table SET items=:foodItem WHERE id=:categoryId ")
    fun updateCard(categoryId:Int,foodItem: String)


}