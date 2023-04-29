package com.example.garageapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garageapp.Repository.mainRepository
import com.example.garageapp.Utils.FirebaseResult
import com.example.garageapp.models.carInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: mainRepository):ViewModel(){

    val _AllMakes: StateFlow<FirebaseResult<List<com.example.garageapp.models.Result>>>
        get() = mainRepository.AllMakes

    val _AllModels: StateFlow<FirebaseResult<List<com.example.garageapp.models.ResultX>>>
        get() = mainRepository.AllModels

    val _AllCars: LiveData<List<carInfo>>
        get() = mainRepository.AllCars
    fun getAllMakes(){
        viewModelScope.launch {
            mainRepository.getAllMakes()
        }
    }
    fun getModelsForMakeID(Make_ID: String){
        viewModelScope.launch {
            mainRepository.getModelsForMakers(Make_ID)
        }
    }
    fun addCarInDB(carInfo: carInfo){
        viewModelScope.launch {
            mainRepository.addCarInDB(carInfo)
        }
    }
    fun getAllCar(){
        viewModelScope.launch {
            mainRepository.getAllCars()
        }
    }
    fun deleteCar(carInfo: carInfo){
        viewModelScope.launch {
            mainRepository.deleteCar(carInfo)
        }
    }
    fun deleteAll(){
        viewModelScope.launch {
            mainRepository.deleteAll()
        }
    }
    fun updateCar(carInfo: carInfo){
        viewModelScope.launch {
            mainRepository.updateCar(carInfo)
        }
    }
}