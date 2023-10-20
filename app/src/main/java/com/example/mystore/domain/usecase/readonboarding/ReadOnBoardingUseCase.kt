package com.example.mystore.domain.usecase.getallproduct

import com.example.mystore.data.repository.Repository
import kotlinx.coroutines.flow.Flow


class ReadOnBoardingUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()

}