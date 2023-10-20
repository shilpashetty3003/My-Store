package com.example.mystore.presentation.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val useCase: UseCase,
) : ViewModel() {

    private val _favouriteList = MutableStateFlow<List<FoodItem>>(emptyList())
    val favouriteList = _favouriteList.asStateFlow()

//    init {
//        getFavouriteProducts()
//    }

    fun getFavouriteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.readFavouriteCart.invoke().collect { value ->
                _favouriteList.value = value
            }
        }
    }

    fun deleteFavouriteCart(foodItem: FoodItem) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavouriteCart(foodItem)
            useCase.readFavouriteCart.invoke().collect { value ->
                _favouriteList.value = value
            }
        }
    }



}