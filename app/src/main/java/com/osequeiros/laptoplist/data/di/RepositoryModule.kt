package com.osequeiros.laptoplist.data.di

import com.osequeiros.laptoplist.data.LaptopDataRepository
import com.osequeiros.laptoplist.domain.repository.LaptopRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun LaptopDataRepository.binds(): LaptopRepository
}