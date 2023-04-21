package com.example.garageapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResultX(
    val Make_ID: Int,
    val Make_Name: String,
    val Model_ID: Int,
    val Model_Name: String
)