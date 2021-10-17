package com.osequeiros.laptoplist.domain

import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.domain.repository.LaptopRepository

class GetLaptopsUseCase constructor(
    private val repository: LaptopRepository,
) {

    operator fun invoke(): List<Laptop> {
        return repository.getLaptops()
    }
}