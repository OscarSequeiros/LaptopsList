package com.osequeiros.laptoplist.data.mapper

import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop
import com.osequeiros.laptoplist.data.remote.model.RemoteLaptop
import com.osequeiros.laptoplist.domain.model.Laptop
import javax.inject.Inject

class LaptopDataMapper @Inject constructor() {

    fun toRoom(laptops: List<RemoteLaptop>): List<RoomLaptop> {
        return laptops.mapIndexed { index, laptop -> laptop.toRoom(index) }
    }

    private fun RemoteLaptop.toRoom(index: Int): RoomLaptop {
        return RoomLaptop(
            id = index.toLong(),
            title = title,
            description = description,
            imageUrl = image
        )
    }

    fun toDomain(laptops: List<RoomLaptop>): List<Laptop> {
        return laptops.map { laptop -> laptop.toDomain() }
    }

    private fun RoomLaptop.toDomain(): Laptop {
        return Laptop(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl
        )
    }
}