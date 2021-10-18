package com.osequeiros.laptoplist.data

import com.osequeiros.laptoplist.data.local.LaptopDao
import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop
import com.osequeiros.laptoplist.data.mapper.LaptopDataMapper
import com.osequeiros.laptoplist.data.remote.LaptopApi
import com.osequeiros.laptoplist.data.remote.model.RemoteLaptop
import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.factory.makeFakeLaptops
import com.osequeiros.laptoplist.factory.makeFakeRemoteLaptops
import com.osequeiros.laptoplist.factory.makeFakeRoomLaptops
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Test

@ExperimentalCoroutinesApi
class LaptopDataRepositoryTest {

    private val localSource = mockk<LaptopDao>()
    private val remoteSource = mockk<LaptopApi>()
    private val mapper = mockk<LaptopDataMapper>()
    private val dataRepository = LaptopDataRepository(
        localSource = localSource,
        remoteSource = remoteSource,
        mapper = mapper
    )

    @Test
    fun `given empty list the first time reading local, when getLaptops, then get laptops remotely`() =
        runBlockingTest {
            val localLaptops = emptyList<RoomLaptop>()
            stubLaptopsInLocal(localLaptops)
            val remoteLaptops = makeFakeRemoteLaptops(3)
            stubLaptopsInRemote(remoteLaptops)
            val mappedLaptops = makeFakeRoomLaptops(3)
            stubMapper(remoteLaptops, mappedLaptops)
            stubSavingInLocal()
            stubLaptopsInLocal(mappedLaptops)
            val fakeLaptops = makeFakeLaptops(3)
            stubMapper(fakeLaptops)

            val laptopsFlow = dataRepository.getLaptops()

            laptopsFlow.collect { laptops ->
                laptops shouldBe fakeLaptops
            }
        }

    @Test
    fun `given no empty list from local, when getLaptops, then get them locally`() =
        runBlockingTest {
            val localLaptops = makeFakeRoomLaptops(5)
            stubLaptopsInLocal(localLaptops)
            val fakeLaptops = makeFakeLaptops(5)
            stubMapper(fakeLaptops)

            val laptopsFlow = dataRepository.getLaptops()

            laptopsFlow.collect { laptops ->
                laptops shouldBe fakeLaptops
            }
        }

    private fun stubLaptopsInLocal(laptops: List<RoomLaptop>) {
        coEvery { localSource.getAll() } coAnswers { laptops }
    }

    private fun stubLaptopsInRemote(laptops: List<RemoteLaptop>) {
        coEvery { remoteSource.getLaptops() } coAnswers { laptops }
    }

    private fun stubMapper(remoteLaptops: List<RemoteLaptop>, roomLaptops: List<RoomLaptop>) {
        every { mapper.toRoom(remoteLaptops) } answers { roomLaptops }
    }

    private fun stubSavingInLocal() {
        coEvery { localSource.save(any()) } coAnswers { }
    }

    private fun stubMapper(laptops: List<Laptop>) {
        every { mapper.toDomain(any()) } answers { laptops }
    }
}