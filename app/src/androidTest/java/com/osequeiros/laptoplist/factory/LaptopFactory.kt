package com.osequeiros.laptoplist.factory

import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop
import com.osequeiros.laptoplist.data.remote.model.RemoteLaptop
import com.osequeiros.laptoplist.domain.model.Laptop
import kotlin.random.Random

fun generateFakeRoomLaptops(size: Int = 4): List<RoomLaptop> {
    return (1..size)
        .map { generateFakeRoomLaptop() }
        .distinctBy { it.id }
}

private fun generateFakeRoomLaptop(): RoomLaptop {
    return RoomLaptop(
        id = Random.nextLong(),
        title = generateRandomString(10),
        description = generateRandomString(35),
        imageUrl = generateRandomString(30),
    )
}