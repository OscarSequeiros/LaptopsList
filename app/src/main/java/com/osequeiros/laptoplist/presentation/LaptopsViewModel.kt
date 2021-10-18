package com.osequeiros.laptoplist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osequeiros.laptoplist.domain.GetLaptopsUseCase
import com.osequeiros.laptoplist.presentation.state.LaptopsViewState
import com.osequeiros.laptoplist.presentation.state.LaptopsViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaptopsViewModel @Inject constructor(
    private val getLaptopsUseCase: GetLaptopsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LaptopsViewState>(LoadingState)

    val state: StateFlow<LaptopsViewState>
        get() = _state

    fun getLaptops() {
        viewModelScope.launch {
            getLaptopsUseCase()
                .map { laptops -> _state.value = SuccessState(laptops) }
                .onStart { _state.value = LoadingState }
                .catch { error -> _state.value = ErrorState(error) }
                .collect()
        }
    }
}