package com.osequeiros.laptoplist.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.osequeiros.laptoplist.data.local.database.AppDataBase
import com.osequeiros.laptoplist.factory.generateFakeRoomLaptops
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LaptopDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDataBase
    private lateinit var dao: LaptopDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()

        dao = database.laptopDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenLaptops_whenSaveAndGetThem_thenGetTheSameList() = runBlockingTest {
        val fakeLaptops = generateFakeRoomLaptops(6)
        dao.save(fakeLaptops)

        val laptops = dao.getAll()

        laptops.forEach { laptop ->
            assert(fakeLaptops.contains(laptop))
        }
    }
}