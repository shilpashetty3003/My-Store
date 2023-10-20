package com.example.mystore.domain.usecase.getallproduct

import com.example.mystore.data.repository.Repository


class SaveOnBoardingUseCase(
    private val repository: Repository,
) {

    suspend operator fun invoke(isCompleted: Boolean) {
        repository.saveOnBoardingState(isCompleted = isCompleted)
    }

}