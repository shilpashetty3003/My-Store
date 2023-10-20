package com.example.mystore.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mystore.utils.Constants
import com.example.mystore.utils.Constants.PRODUCT_DATABASE_TABLE
import com.example.mystore.utils.Converter


@Entity(tableName = Constants.PRODUCT_DATABASE_TABLE)
data class CategoryItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var name: String,
    val items:ArrayList<FoodItem>,
)

