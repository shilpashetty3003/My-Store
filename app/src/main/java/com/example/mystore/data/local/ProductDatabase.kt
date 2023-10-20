package com.example.mystore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mystore.data.local.dao.ProductDao
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.utils.Converter

@Database(entities = [CategoryItem::class], version = 1)
@TypeConverters(Converter::class)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}