package com.example.garageapp.Repository

import androidx.lifecycle.MutableLiveData
import com.example.garageapp.Utils.FirebaseResult
import com.example.garageapp.api.carAPI
import com.example.garageapp.db.carDB
import com.example.garageapp.models.carInfo
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class mainRepository @Inject constructor(private val carAPI: carAPI, private val carDB: carDB){

    private val _AllMakes= MutableStateFlow<FirebaseResult<List<com.example.garageapp.models.Result>>>(FirebaseResult.Loading())
    val AllMakes: MutableStateFlow<FirebaseResult<List<com.example.garageapp.models.Result>>> = _AllMakes

    private val _AllModels= MutableStateFlow<FirebaseResult<List<com.example.garageapp.models.ResultX>>>(FirebaseResult.Loading())
    val AllModels: MutableStateFlow<FirebaseResult<List<com.example.garageapp.models.ResultX>>> = _AllModels

    private val _AllCars= MutableLiveData<List<carInfo>>()
    val AllCars: MutableLiveData<List<carInfo>> = _AllCars
    suspend fun getAllMakes(){
        val response= carAPI.getAllMakes()
        if (response.isSuccessful && response.body() != null) {
            AllMakes.emit(FirebaseResult.Success(response.body()!!.Results))
        }
    }

    suspend fun getModelsForMakers(Make_ID: String){
        val response= carAPI.getModels(Make_ID)
        if (response.isSuccessful && response.body() != null) {
            AllModels.emit(FirebaseResult.Success(response.body()!!.Results))
        }

    }
    suspend fun addCarInDB(carInfo: carInfo){
        carDB.getCarDao().addCar(carInfo)
        val Cars= carDB.getCarDao().getAllcar()
        AllCars.postValue(Cars)
    }
    suspend fun getAllCars(){
        val Cars= carDB.getCarDao().getAllcar()
        AllCars.postValue(Cars)
    }
    suspend fun deleteCar(carInfo: carInfo){
        carDB.getCarDao().deleteCar(carInfo)
        val Cars= carDB.getCarDao().getAllcar()
        AllCars.postValue(Cars)
    }
    suspend fun updateCar(carInfo: carInfo){
        carDB.getCarDao().updateCar(carInfo)
        val Cars= carDB.getCarDao().getAllcar()
        AllCars.postValue(Cars)
    }
    suspend fun deleteAll(){
        carDB.getCarDao().deleteAll()
    }
}