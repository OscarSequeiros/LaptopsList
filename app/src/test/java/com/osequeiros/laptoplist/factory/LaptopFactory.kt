package com.osequeiros.laptoplist.factory

import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop
import com.osequeiros.laptoplist.data.remote.model.RemoteLaptop
import com.osequeiros.laptoplist.domain.model.Laptop
import kotlin.random.Random

fun makeFakeLaptops(size: Int = 4): List<Laptop> {
    return (1..size).map { makeFakeLaptop() }
}

private fun makeFakeLaptop(): Laptop {
    return Laptop(
        id = Random.nextLong(),
        title = makeRandomString(10),
        description = makeRandomString(35),
        imageUrl = makeFakeImageUrl(),
    )
}

fun makeFakeRemoteLaptops(size: Int = 4): List<RemoteLaptop> {
    return (1..size).map { makeFakeRemoteLaptop() }
}

private fun makeFakeRemoteLaptop(): RemoteLaptop {
    return RemoteLaptop(
        title = makeRandomString(10),
        description = makeRandomString(35),
        image = makeFakeImageUrl(),
    )
}

fun makeFakeRoomLaptops(size: Int = 4): List<RoomLaptop> {
    return (1..size).map { makeFakeRoomLaptop() }
}

private fun makeFakeRoomLaptop(): RoomLaptop {
    return RoomLaptop(
        id = Random.nextLong(),
        title = makeRandomString(10),
        description = makeRandomString(35),
        imageUrl = makeFakeImageUrl(),
    )
}

private fun makeFakeImageUrl(): String {
    val image = Random.nextInt(10)
    return "https://picsum.photos/300/300?image=$image"
}