package com.osequeiros.laptoplist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.osequeiros.laptoplist.data.local.LaptopDao
import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop

@Database(
    entities = [
        RoomLaptop::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun laptopDao(): LaptopDao
}