package com.example.garageapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class carInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val carMaker: String,
    val carModel:String,
    val carImage: String?
)
