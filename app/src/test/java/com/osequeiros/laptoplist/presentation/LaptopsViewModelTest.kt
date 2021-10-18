package com.osequeiros.laptoplist.presentation

import com.osequeiros.laptoplist.domain.GetLaptopsUseCase
import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.factory.makeFakeLaptops
import com.osequeiros.laptoplist.presentation.state.LaptopsViewState
import com.osequeiros.laptoplist.util.CoroutineTestRule
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LaptopsViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val useCase = mockk<GetLaptopsUseCase>()
    private val viewModel = LaptopsViewModel(
        getLaptopsUseCase = useCase
    )

    @Test
    fun `given laptops by use case, when getLaptops and read state, then expose SuccessState in the end`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val fakeLaptops = makeFakeLaptops(6)
            stubLaptopsInUseCase(fakeLaptops)

            viewModel.getLaptops()

            viewModel.state.value shouldBe LaptopsViewState.SuccessState(fakeLaptops)
        }
    }

    private fun stubLaptopsInUseCase(laptops: List<Laptop>) {
        coEvery { useCase() } coAnswers { flowOf(laptops) }
    }

    @Test
    fun `given an error by use case, when getLaptops and read state, then expose ErrorState in the end`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val fakeError = Throwable()
            stubErrorInUseCase(fakeError)

            viewModel.getLaptops()

            viewModel.state.value shouldBe LaptopsViewState.ErrorState(fakeError)
        }
    }

    private fun stubErrorInUseCase(error: Throwable) {
        coEvery { useCase() } coAnswers { flow { throw error } }
    }
}