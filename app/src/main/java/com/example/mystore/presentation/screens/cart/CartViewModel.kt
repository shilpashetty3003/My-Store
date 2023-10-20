package com.example.mystore.presentation.screens.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.domain.model.CategoryItem
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {
    private val _productList = MutableStateFlow<List<FoodItem>>(emptyList())
    val productList = _productList.asStateFlow()


    fun getCartList() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.readCartUseCase.invoke().collectLatest { value ->
                _productList.value = value
            }
        }
    }



    fun deleteCart(foodItem: FoodItem) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteCart.invoke(foodItem)
            getCartList()
        }
    }
}