package com.osequeiros.laptoplist.domain.repository

import com.osequeiros.laptoplist.domain.model.Laptop
import kotlinx.coroutines.flow.Flow

interface LaptopRepository {

    fun getLaptops(): Flow<List<Laptop>>
}