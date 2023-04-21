package com.example.garageapp.db

import androidx.room.*
import com.example.garageapp.models.carInfo

@Dao
interface carDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(CarInfo: carInfo)

    @Query("Select * from carInfo")
    suspend fun getAllcar():List<carInfo>

    @Delete
    suspend fun deleteCar(carInfo: carInfo)

    @Update
    suspend fun updateCar(carInfo: carInfo)

    @Query("DELETE FROM carInfo")
    suspend fun deleteAll()
}