package com.osequeiros.laptoplist.data

import com.osequeiros.laptoplist.data.local.LaptopDao
import com.osequeiros.laptoplist.data.mapper.LaptopDataMapper
import com.osequeiros.laptoplist.data.remote.LaptopApi
import com.osequeiros.laptoplist.domain.model.Laptop
import com.osequeiros.laptoplist.domain.repository.LaptopRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LaptopDataRepository @Inject constructor(
    private val localSource: LaptopDao,
    private val remoteSource: LaptopApi,
    private val mapper: LaptopDataMapper,
): LaptopRepository {

    // TODO: [Tech note] The strategy here is to read the data remotely
    //  only if the local list is empty. The approach could be different, of course. :wink:
    override fun getLaptops(): Flow<List<Laptop>> {
        return flow {

            val localLaptops = localSource.getAll().ifEmpty {
                val remoteLaptops = remoteSource.getPosts()
                val newLaptops = mapper.toRoom(remoteLaptops)
                localSource.save(newLaptops)
                localSource.getAll()
            }

            val users = mapper.toDomain(localLaptops)
            emit(users)
        }
    }
}