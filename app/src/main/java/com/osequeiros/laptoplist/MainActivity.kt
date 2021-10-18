package com.osequeiros.laptoplist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.osequeiros.laptoplist.presentation.LaptopsViewModel
import com.osequeiros.laptoplist.presentation.state.LaptopsViewState
import com.osequeiros.laptoplist.presentation.state.LaptopsViewState.*
import com.osequeiros.laptoplist.ui.state.Laptops
import com.osequeiros.laptoplist.ui.state.Loader
import com.osequeiros.laptoplist.ui.theme.LaptopsListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: LaptopsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getLaptops()
        setContent {
            LaptopsListTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val viewState by viewModel.state.collectAsState()
                    Render(viewState)
                }
            }
        }
    }

    @Composable
    private fun Render(uiState: LaptopsViewState) {
        when (uiState) {
            is IdleState -> {}
            is LoadingState -> Loader()
            is SuccessState -> Laptops(laptops = uiState.laptops)
            is ErrorState -> {}
        }
    }
}