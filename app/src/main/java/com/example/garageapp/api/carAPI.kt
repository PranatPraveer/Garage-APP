package com.example.garageapp.api

import com.example.garageapp.models.AllMakes
import com.example.garageapp.models.ModelsForMaker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface carAPI {

    @GET("getallmakes")
    suspend fun getAllMakes(
        @Query("format") format: String = "json"
    ): Response<AllMakes>

    @GET("GetModelsForMakeId/{MakeId}")
    suspend fun getModels(
        @Path("MakeId") makeId:String,
        @Query("format") format: String = "json"
    ): Response<ModelsForMaker>
}
