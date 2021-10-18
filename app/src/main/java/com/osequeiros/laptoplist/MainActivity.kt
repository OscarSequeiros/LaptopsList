package com.osequeiros.laptoplist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

import com.osequeiros.laptoplist.presentation.LaptopsViewModel
import com.osequeiros.laptoplist.presentation.state.LaptopsUiState
import com.osequeiros.laptoplist.presentation.state.LaptopsUiState.*
import com.osequeiros.laptoplist.ui.state.Laptops
import com.osequeiros.laptoplist.ui.theme.LaptopsListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: LaptopsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaptopsListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val uiState = viewModel
                        .getLaptops()
                        .collectAsState(initial = LoadingState)
                    Render(uiState.value)
                }

            }
        }
    }

    @Composable
    private fun Render(uiState: LaptopsUiState) {
        when (uiState) {
            is LoadingState -> Loader()
            is SuccessState -> Laptops(laptops = uiState.laptops)
            else -> Log.e("uiState", uiState.toString())
        }
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loaderdots))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition,
        progress,
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LaptopsListTheme {
        Greeting("Android")
    }
}