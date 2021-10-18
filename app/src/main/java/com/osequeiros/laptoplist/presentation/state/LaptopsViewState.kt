package com.osequeiros.laptoplist.presentation.state

import com.osequeiros.laptoplist.domain.model.Laptop

sealed class LaptopsViewState {

    object IdleState : LaptopsViewState()

    object LoadingState : LaptopsViewState()

    data class SuccessState(val laptops: List<Laptop>) : LaptopsViewState()

    data class ErrorState(val error: Throwable) : LaptopsViewState()
}