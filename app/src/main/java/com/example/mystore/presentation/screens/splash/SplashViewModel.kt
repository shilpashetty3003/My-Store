package com.example.mystore.presentation.screens.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.domain.usecase.UseCase
import com.example.mystore.utils.DataDummy
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SplashViewModel @Inject constructor(
    private val useCases: UseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val _onBoardingIsCompleted = MutableStateFlow(false)
    val onBoardingIsCompleted: StateFlow<Boolean> = _onBoardingIsCompleted

    init {
        viewModelScope.launch {
            useCases.insertProductsUseCase.invoke(DataDummy.generateDummyProduct(context))
        }

        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingIsCompleted.value =
                useCases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }
}