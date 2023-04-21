package com.example.garageapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.garageapp.models.ResultX
import com.example.garageapp.models.carInfo

@Database(entities = [carInfo::class], version = 1)
abstract class carDB: RoomDatabase() {
    abstract fun getCarDao():carDAO
}