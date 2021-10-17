package com.osequeiros.laptoplist.factory

import com.osequeiros.laptoplist.domain.model.Laptop
import kotlin.random.Random

fun makeFakeLaptops(size: Int = 4): List<Laptop> {
    return (1..size).map { makeFakeLaptop() }
}

private fun makeFakeLaptop(): Laptop {
    val image = Random.nextInt(10)
    return Laptop(
        title = makeRandomString(10),
        description = makeRandomString(35),
        imageUrl = "https://picsum.photos/300/300?image=$image"
    )
}