package com.example.mystore.utils

import androidx.room.TypeConverter
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromJson(json: String): ArrayList<FoodItem> {
        val type = object : TypeToken<List<FoodItem>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(list: ArrayList<FoodItem>): String {
        return Gson().toJson(list)
    }
}