package com.example.mystore.presentation.screens.home

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
class HomeViewModel @Inject constructor(
    private val useCases: UseCase,
) : ViewModel() {

    private val _productList = MutableStateFlow<List<CategoryItem>>(emptyList())
    val productList = _productList.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getAllProductUseCase.invoke().collect { value ->
                _productList.value = value
            }
        }
    }

    fun addCart(categoryId: Int, foodItem: FoodItem) = viewModelScope.launch {
        useCases.insertCartUseCase(categoryId, foodItem)
    }



}