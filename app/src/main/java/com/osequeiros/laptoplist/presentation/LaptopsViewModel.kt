package com.osequeiros.laptoplist.presentation

import androidx.lifecycle.ViewModel
import com.osequeiros.laptoplist.domain.GetLaptopsUseCase
import com.osequeiros.laptoplist.presentation.state.LaptopsUiState
import com.osequeiros.laptoplist.presentation.state.LaptopsUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LaptopsViewModel @Inject constructor(
    private val getLaptopsUseCase: GetLaptopsUseCase
) : ViewModel() {

    fun getLaptops(): Flow<LaptopsUiState> {
        return getLaptopsUseCase()
            .map { laptops -> SuccessState(laptops) as LaptopsUiState }
            .onStart { emit(LoadingState) }
            .catch { error -> emit(ErrorState(error)) }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }
}