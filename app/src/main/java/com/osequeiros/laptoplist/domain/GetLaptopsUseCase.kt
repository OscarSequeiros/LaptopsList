package com.osequeiros.laptoplist.domain

import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.domain.repository.LaptopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLaptopsUseCase @Inject constructor(
    private val repository: LaptopRepository,
) {

    operator fun invoke(): Flow<List<Laptop>> {
        return repository.getLaptops()
    }
}