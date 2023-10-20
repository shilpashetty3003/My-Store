package com.example.mystore.presentation.screens.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.domain.model.FoodItem
import com.example.mystore.domain.usecase.UseCase
import com.example.mystore.utils.Constants.PRODUCT_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: UseCase,
    saveStatedHandle: SavedStateHandle,
) : ViewModel() {

    private val _selectedProduct: MutableStateFlow<FoodItem?> = MutableStateFlow(null)
    val selectedProduct: StateFlow<FoodItem?> = _selectedProduct

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productId = saveStatedHandle.get<Int>(PRODUCT_ARGUMENT_KEY) ?: 0
            Log.d("TAGproductIdproductId", ": $productId")
            useCase.readSelectedProductUseCase.invoke(productId = productId).collectLatest {
                _selectedProduct.value = it
            }
        }
    }
}