package com.osequeiros.laptoplist.data.mapper

import com.osequeiros.laptoplist.factory.makeFakeRemoteLaptops
import com.osequeiros.laptoplist.factory.makeFakeRoomLaptops
import io.kotest.matchers.shouldBe
import org.junit.Test

class LaptopDataMapperTest {

    private val mapper = LaptopDataMapper()

    @Test
    fun `given a list of remote laptops, when toRoom, then get a list of room laptops`() {
        val remoteLaptops = makeFakeRemoteLaptops(4)

        val roomLaptops = mapper.toRoom(remoteLaptops)

        roomLaptops.zip(remoteLaptops).forEachIndexed { index, (roomLaptop, remoteLaptop) ->
            roomLaptop.id shouldBe index + 1
            roomLaptop.title shouldBe remoteLaptop.title
            roomLaptop.description shouldBe remoteLaptop.description
            roomLaptop.imageUrl shouldBe remoteLaptop.image
        }
    }

    @Test
    fun `given a list of room laptops, when toDomain, then get a list of laptops`() {
        val roomLaptops = makeFakeRoomLaptops(6)

        val laptops = mapper.toDomain(roomLaptops)

        laptops.zip(roomLaptops).forEach { (laptop, roomLaptop) ->
            laptop.id shouldBe roomLaptop.id
            laptop.title shouldBe roomLaptop.title
            laptop.description shouldBe roomLaptop.description
            laptop.imageUrl shouldBe roomLaptop.imageUrl
        }
    }
}