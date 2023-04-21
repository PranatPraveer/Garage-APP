package com.example.garageapp.models

data class AllMakes(
    val Count: Int,
    val Message: String,
    val Results: List<Result>,
    val SearchCriteria: Any
)