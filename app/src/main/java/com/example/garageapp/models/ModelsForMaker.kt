package com.example.garageapp.models

data class ModelsForMaker(
    val Count: Int,
    val Message: String,
    val Results: List<ResultX>,
    val SearchCriteria: String
)