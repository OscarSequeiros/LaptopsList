package com.osequeiros.laptoplist.domain

import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.domain.repository.LaptopRepository
import kotlinx.coroutines.flow.Flow

class GetLaptopsUseCase constructor(
    private val repository: LaptopRepository,
) {

    operator fun invoke(): Flow<List<Laptop>> {
        return repository.getLaptops()
    }
}