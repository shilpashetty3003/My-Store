package com.example.mystore.utils

import android.content.Context
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataDummy {

    fun generateDummyProduct(context: Context): List<CategoryItem> {

        val jsonFileName = "shopping.json"
        val jsonString = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }

        val items: List<CategoryItem> =
            Gson().fromJson(jsonString, object : TypeToken<List<CategoryItem>>() {}.type)
        return items
    }
}