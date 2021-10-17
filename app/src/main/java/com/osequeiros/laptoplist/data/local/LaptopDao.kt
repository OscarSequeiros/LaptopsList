package com.osequeiros.laptoplist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osequeiros.laptoplist.data.local.database.model.RoomLaptop

@Dao
interface LaptopDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(laptops: List<RoomLaptop>)

    @Query("SELECT * FROM RoomLaptop")
    suspend fun getAll(): List<RoomLaptop>
}