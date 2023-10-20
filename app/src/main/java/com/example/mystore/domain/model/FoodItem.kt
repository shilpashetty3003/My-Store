package com.example.mystore.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mystore.utils.Constants



data class FoodItem(

    val id: Int =0,
    val name: String="",
    val icon: String="",
    val price: Double=0.0,
    var isCart:Boolean = false,
    var isFavourite:Boolean =false
)
