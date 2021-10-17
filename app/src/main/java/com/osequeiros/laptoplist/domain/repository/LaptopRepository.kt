package com.osequeiros.laptoplist.domain.repository

import com.osequeiros.laptoplist.domain.model.Laptop

interface LaptopRepository {

    fun getLaptops(): List<Laptop>
}