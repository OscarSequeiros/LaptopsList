package com.osequeiros.laptoplist.domain

import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.domain.repository.LaptopRepository
import com.osequeiros.laptoplist.factory.makeFakeLaptops
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetLaptopsUseCaseTest {

    private val repository = mockk<LaptopRepository>()
    private val getLaptopsUseCase = GetLaptopsUseCase(repository)

    @Test
    fun `given laptops from repository, when invoke, then get the same list`() = runBlocking {
        val fakeLaptops = makeFakeLaptops(5)
        stubLaptopsInRepository(fakeLaptops)

        val laptopsFlow = getLaptopsUseCase()

        laptopsFlow.collect { laptops ->
            laptops shouldBe fakeLaptops
        }
    }

    private fun stubLaptopsInRepository(laptops: List<Laptop>) {
        coEvery { repository.getLaptops() } coAnswers { flowOf(laptops) }
    }
}