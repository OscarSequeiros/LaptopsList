package com.osequeiros.laptoplist.data.local.database.di

import android.content.Context
import androidx.room.Room
import com.osequeiros.laptoplist.data.local.LaptopDao
import com.osequeiros.laptoplist.data.local.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: AppDataBase): LaptopDao {
        return appDatabase.laptopDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            DATABASE_NAME
        ).build()

    }

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}