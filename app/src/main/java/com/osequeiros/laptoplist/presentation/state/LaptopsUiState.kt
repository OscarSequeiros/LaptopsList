package com.osequeiros.laptoplist.presentation.state

import com.osequeiros.laptoplist.domain.model.Laptop

sealed class LaptopsUiState {

    object LoadingState : LaptopsUiState()

    data class SuccessState(val laptops: List<Laptop>) : LaptopsUiState()

    data class ErrorState(val error: Throwable) : LaptopsUiState()
}